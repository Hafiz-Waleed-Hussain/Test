package uwanttolearn.astro.core.ui_views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextSwitcher
import android.widget.TextView
import uwanttolearn.astro.R

/**
 * Created by waleed on 30/07/2017.
 */
class NetworkNotAvailableView : TextView {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        gravity = Gravity.CENTER
        setSingleLine(true)
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10f)
        setTextColor(resources.getColor(android.R.color.white))
        setBackgroundColor(R.color.networkNotAvailableBackground)
        text = context.getString(R.string.common_label_no_internet_connection)

    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver,
                intentFilter)


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        context.unregisterReceiver(networkReceiver)

    }


    private fun internetNotavailable(context: Context) {
        visibility = View.VISIBLE
    }

    private var isConnected: Boolean = false
    private var internetCount = 0

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action != null && ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                if (isNetworkAvailable && !isConnected) {
                    isConnected = true
                    internetCount = 0
                    visibility = View.GONE

                } else if (!isNetworkAvailable && internetCount == 0) {
                    internetCount++
                    isConnected = false
                    internetNotavailable(context)
                }
            }
        }
    }


    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
                    && activeNetworkInfo.isAvailable
        }
}
