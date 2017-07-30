package uwanttolearn.astro.feature_tv_guide

import io.reactivex.Scheduler
import uwanttolearn.astro.core.data.pojos.TVGuideChannel

/**
 * Created by waleed on 29/07/2017.
 */
interface TVGuideFragmentContract {
    fun addData(tvGuide: MutableList<TVGuideChannel>)
    fun downloadComplete()
    fun registerForDownloadCompleteReceiver()
    fun unregisterForDownloadCompleteReceiver()
    fun showApiError(throwable: Throwable)
    fun getIOScheduler(): Scheduler
    fun getAndroidMainScheduler(): Scheduler
    fun isNetworkAvailable(): Boolean

}