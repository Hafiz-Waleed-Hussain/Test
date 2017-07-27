package uwanttolearn.astro.home_feature

import android.databinding.BaseObservable
import android.databinding.ObservableInt
import android.view.View
import io.realm.OrderedCollectionChangeSet
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmChangeListener
import io.realm.RealmResults
import timber.log.Timber
import uwanttolearn.astro.core.data.ChannelsRepository
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.core.data.source.ChannelsDataSource

/**
 * Created by waleed on 27/07/2017.
 */
class HomeViewModel(val channelsRepository: ChannelsDataSource,
                    val view: HomeFragmentContract) : BaseObservable() {

    private var allChannelsInfo: RealmResults<ChannelInfo>?

    init {
        channelsRepository.open()
        allChannelsInfo = channelsRepository.getAllChannelsInfo("channelId")
    }

    val progressBarVisibilityHandler = ObservableInt(View.VISIBLE)

    fun onCreateView() {

        channelsRepository.addListener(allChannelsInfo, listener)
        progressBarVisibilityHandler.set(View.GONE)
        view.addData(allChannelsInfo)

    }


    private val listener = OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>> {
        realmResults: RealmResults<ChannelInfo>, orderedCollectionChangeSet: OrderedCollectionChangeSet ->
        view.notifyDataChanged()
    }

    fun onDestroyView() {
        channelsRepository.removeListener(allChannelsInfo, listener)
        channelsRepository.close()

    }

    fun sortDataBy(title: CharSequence?) {

        view.resetAdapter()
        channelsRepository.reset()
        allChannelsInfo = channelsRepository.getAllChannelsInfo(title.toString())
        onCreateView()
    }


}