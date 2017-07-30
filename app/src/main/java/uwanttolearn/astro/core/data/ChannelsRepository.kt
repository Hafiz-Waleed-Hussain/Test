package uwanttolearn.astro.core.data

import com.uwanttolearn.datamodule.network.AstroAppService
import io.reactivex.Observable
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import uwanttolearn.astro.core.data.json.ChannelLists
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.core.data.source.local.ChannelsLocalDataSource
import uwanttolearn.astro.core.data.source.remote.RemoteAstroDataSource

/**
 * Created by waleed on 26/07/2017.
 */
class ChannelsRepository : ChannelsDataSource {


    private lateinit var localChannelDataSource: ChannelsDataSource


    override fun open() {
        localChannelDataSource = ChannelsLocalDataSource()
        localChannelDataSource.open()
    }

    override fun close() {
        localChannelDataSource.close()
    }

    override fun executeTransaction(transaction: Realm.Transaction) {
        localChannelDataSource.executeTransaction(transaction)
    }

    override fun addListener(channelInfo: MutableList<ChannelInfo>?, listener: OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>>) {
        localChannelDataSource.addListener(channelInfo, listener)
    }

    override fun removeListener(channelInfo: MutableList<ChannelInfo>?, listener: OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>>) {
        localChannelDataSource.removeListener(channelInfo, listener)
    }

    override fun getAllChannelsInfo(fieldName: String) = localChannelDataSource.getAllChannelsInfo(fieldName)

    override fun getAllChannelNumbersCopy(): List<Int>? = localChannelDataSource.getAllChannelNumbersCopy()

    override fun reset() {
        localChannelDataSource.reset()
    }


}

