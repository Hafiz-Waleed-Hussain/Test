package uwanttolearn.astro.core.abstracts

import android.app.Dialog
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import uwanttolearn.astro.R
import java.io.IOException
import java.net.SocketException
import java.util.ArrayList

/**
 * Created by waleed on 24/07/2017.
 */
abstract class AstroActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog
    private lateinit var mNetworkReceiver: NetworkReceiver
    private lateinit var mNetworkInformer: MutableList<NetworkInformer>

    private var isConnected: Boolean = false
    private var internetCount = 0

    abstract fun onInternetConnected()

    abstract fun onInternetDisconnected()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = ProgressDialog(this) as Dialog
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        mNetworkInformer = ArrayList<NetworkInformer>()
        mNetworkReceiver = NetworkReceiver()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(mNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        if (mNetworkReceiver != null) {
            unregisterReceiver(mNetworkReceiver)
        }
    }

    fun showProgressDialog() {
        dialog.show()

    }

    fun hideProgressDialog() {
        dialog.dismiss()
    }

    fun getIOScheduler(): Scheduler {
        return Schedulers.io()
    }


    fun getAndroidMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


    fun apiError(throwable: Throwable) {
        hideProgressDialog()
        Timber.e(throwable)
        if (throwable is SocketException)
            Toast.makeText(this, getString(R.string.common_message_error_server_not_responding), Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()

    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
                && activeNetworkInfo.isAvailable
    }

    fun setNetworkInformer(mNetworkInformer: NetworkInformer) {
        this.mNetworkInformer.add(mNetworkInformer)
    }

    fun removeNetworkInformer(networkInformer: NetworkInformer) {
        this.mNetworkInformer.remove(networkInformer)
    }

    private inner class NetworkReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if (intent.action != null && ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                if (isNetworkAvailable() && !isConnected) {
                    isConnected = true
                    internetCount = 0
                    onInternetConnected()
                    if (mNetworkInformer.size > 0)
                        for (networkInformer in mNetworkInformer)
                            networkInformer.networkConnected()

                } else if (!isNetworkAvailable() && internetCount == 0) {
                    internetCount++
                    isConnected = false
                    onInternetDisconnected()
                    if (mNetworkInformer.size > 0)
                        for (networkInformer in mNetworkInformer)
                            networkInformer.networkDisconnected()
                }
            }
        }
    }
}

interface NetworkInformer {
    fun networkConnected()

    fun networkDisconnected()
}