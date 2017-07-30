package uwanttolearn.astro.feature_tv_guide

import android.databinding.ObservableInt
import android.view.View
import uwanttolearn.astro.core.data.json.TVGuide
import uwanttolearn.astro.core.data.pojos.TVGuideChannel
import uwanttolearn.astro.core.data.pojos.TVGuideEvent
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.ChannelsDataSource

/**
 * Created by waleed on 29/07/2017.
 */
class TVGuideFragmentViewModel(val astroRepository: AstroRepositoryDataSource,
                               val channelRepository: ChannelsDataSource,
                               val view: TVGuideFragmentContract) {

    val progressBarVisibilityHandler = ObservableInt(View.VISIBLE)
    private var allChannelNumbersCopy: List<Int>? = null
    private var previousIndex = 0
    private var index = 0
    private var lastDownloadedIndex = 0
    private val PAGE_SIZE = 10
    private var isReceiverRegistered = false


    fun onViewCreated() {
        channelRepository.open()
        if (view.isNetworkAvailable())
            startDataDownloading()
        else
            progressBarVisibilityHandler.set(View.GONE)
    }


    fun onDestroyView() {
        channelRepository.close()
        if (isReceiverRegistered)
            view.unregisterForDownloadCompleteReceiver()
    }

    fun loadMoreData() {
        setIndexes()
        if (previousIndex == index) {
            view.downloadComplete()
        } else {
            val list = allChannelNumbersCopy?.subList(previousIndex, index)
            list?.let {

                astroRepository.getTodayEvents(it.map { "$it" })
                        .subscribeOn(view.getIOScheduler())
                        .observeOn(view.getAndroidMainScheduler())
                        .subscribe({ success(list, it) }, { view.showApiError(it) })
            }
        }
    }


    private fun setIndexes() {
        if (lastDownloadedIndex == index) {
            previousIndex = index
            allChannelNumbersCopy?.let {
                if (it.size > index + PAGE_SIZE)
                    index += PAGE_SIZE
                else
                    index += it.size - 1 - index
            }
        }
    }

    fun channelsDataDownloaded() {
        startDataDownloading()
        view.unregisterForDownloadCompleteReceiver()
    }

    private fun success(list: List<Int>, it: TVGuide) {
        progressBarVisibilityHandler.set(View.GONE)
        val tvGuide: MutableList<TVGuideChannel> = arrayListOf()

        for (i in list) {
            val map = it.getevent.filter { it.channelId.toInt() == i }
                    .map {
                        TVGuideEvent(it.displayDateTime, it.programmeTitle, it.programmeId)
                    }
            val first = it.getevent.filter { it.channelId.toInt() == i }.firstOrNull()
            first?.let {
                tvGuide.add(
                        TVGuideChannel(first.channelId, first.channelStbNumber.toLong(), first.channelTitle, "", map as MutableList<TVGuideEvent>))
            }
        }
        view.addData(tvGuide)
        lastDownloadedIndex = index
    }

    private fun startDataDownloading() {
        allChannelNumbersCopy = channelRepository.getAllChannelNumbersCopy()
        allChannelNumbersCopy?.let {
            if (it.size > 0)
                loadMoreData()
            else {
                view.registerForDownloadCompleteReceiver()
                isReceiverRegistered = true
                progressBarVisibilityHandler.set(View.GONE)
            }
        }
    }

    fun internetConnected() {
        if (lastDownloadedIndex == 0)
            progressBarVisibilityHandler.set(View.VISIBLE)
        startDataDownloading()
    }

    fun internetDisconnected() {
        progressBarVisibilityHandler.set(View.GONE)
    }


}