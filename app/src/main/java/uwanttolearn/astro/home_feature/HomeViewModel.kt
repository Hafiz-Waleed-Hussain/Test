package uwanttolearn.astro.home_feature

import android.databinding.BaseObservable
import android.databinding.ObservableInt
import android.view.View
import io.realm.*
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

    private var selectedSort: String = "channelId"

    init {
        channelsRepository.open()
        allChannelsInfo = channelsRepository.getAllChannelsInfo(selectedSort)
    }

    val progressBarVisibilityHandler = ObservableInt(View.VISIBLE)

    fun onCreateView() {

        channelsRepository.addListener(allChannelsInfo, listener)
        progressBarVisibilityHandler.set(View.GONE)
        view.addData(allChannelsInfo)

    }


    private val listener = OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>> {
        realmResults: RealmResults<ChannelInfo>, orderedCollectionChangeSet: OrderedCollectionChangeSet ->

        if (orderedCollectionChangeSet == null)
            return@OrderedRealmCollectionChangeListener

        if (orderedCollectionChangeSet.insertionRanges.size > 0) {
            view.addData(realmResults)
            view.notifyDataChanged()
        }


        if (orderedCollectionChangeSet.changes.size > 0) {
            for (index in orderedCollectionChangeSet.changes) {
                view.updateRowOnPosition(index)
            }
        }


    }

    fun onDestroyView() {
        channelsRepository.removeListener(allChannelsInfo, listener)
        channelsRepository.close()

    }

    fun sortDataBy(title: CharSequence?) {
        selectedSort = title.toString()
        view.resetAdapter()
        channelsRepository.reset()
        allChannelsInfo = channelsRepository.getAllChannelsInfo(selectedSort)
        onCreateView()
    }

    fun saveClick(pair: Pair<ChannelInfo, Boolean>) {
        channelsRepository.executeTransaction(Realm.Transaction {
            pair.first.isSave = pair.second
            it.insertOrUpdate(pair.first)
        })
        if (selectedSort.equals("isSave"))
            sortDataBy("isSave")

    }


}