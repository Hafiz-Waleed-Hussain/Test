package uwanttolearn.astro.core.abstracts

import android.content.Context
import android.support.v4.app.Fragment
import io.reactivex.Scheduler

/**
 * Created by waleed on 25/07/2017.
 */
abstract class AstroFragment : Fragment() {

    protected lateinit var activity: AstroActivity

    abstract fun onInternetConnected()

    abstract fun onInternetDisconnected()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as AstroActivity
        setHasOptionsMenu(true)
        activity.setNetworkInformer(mNetworkInformer)
    }

    override fun onDetach() {
        super.onDetach()
        activity.removeNetworkInformer(mNetworkInformer)
    }


    fun showDialog() {
        activity.showProgressDialog()
    }

    fun hideDialog() {
        activity.hideProgressDialog()
    }

    fun showApiError(throwable: Throwable) {
        activity.apiError(throwable)
    }


    fun getIOScheduler(): Scheduler {
        return activity.getIOScheduler()
    }

    fun getAndroidMainScheduler(): Scheduler {
        return activity.getAndroidMainScheduler()
    }


    private val mNetworkInformer = object : NetworkInformer {
        override fun networkConnected() {
            onInternetConnected()
        }

        override fun networkDisconnected() {
            onInternetDisconnected()
        }
    }


    fun isNetworkAvailable(): Boolean {
        return activity.isNetworkAvailable()
    }
}