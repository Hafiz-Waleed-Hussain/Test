package uwanttolearn.astro.tv_guide

import android.databinding.ObservableInt
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uwanttolearn.astro.core.data.ChannelsRepository
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
    private val PAGE_SZIE = 10


    fun onViewCreated() {
        channelRepository.open()
        allChannelNumbersCopy = channelRepository.getAllChannelNumbersCopy()
        loadMoreData()
    }

    fun onDestroyView() {
        channelRepository.close()
    }

    fun loadMoreData() {

        setIndexes()
        if (previousIndex == index) {
            view.downloadComplete()
        } else {
            val list = allChannelNumbersCopy?.subList(previousIndex, index)
            list?.let {

                astroRepository.getTodayEvents(it.map { "$it" })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
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
            }
        }
    }


    private fun setIndexes() {
        if (lastDownloadedIndex == index) {
            previousIndex = index
            allChannelNumbersCopy?.let {
                if (it.size > index + PAGE_SZIE)
                    index += PAGE_SZIE
                else
                    index += it.size - 1 - index
            }
        }
    }


}