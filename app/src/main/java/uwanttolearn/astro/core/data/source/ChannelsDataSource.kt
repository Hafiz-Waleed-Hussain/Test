package uwanttolearn.astro.core.data.source

import io.realm.OrderedRealmCollectionChangeListener
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import uwanttolearn.astro.core.data.pojos.ChannelInfo

/**
 * Created by waleed on 26/07/2017.
 */
interface ChannelsDataSource {

    fun open()

    fun close()

    fun executeTransaction(transaction: Realm.Transaction)

    fun addListener(channelInfo: MutableList<ChannelInfo>?, listener: OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>>)

    fun removeListener(channelInfo: MutableList<ChannelInfo>?, listener: OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>>)

    fun getAllChannelsInfo(filedName: String): RealmResults<ChannelInfo>?

    fun getAllChannelNumbersCopy(): List<Int>?

    fun reset()
}