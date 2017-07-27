package uwanttolearn.astro.core.data.source.local

import io.realm.*
import org.jetbrains.annotations.NotNull
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.core.data.source.ChannelsDataSource

/**
 * Created by waleed on 26/07/2017.
 */
class ChannelsLocalDataSource : ChannelsDataSource {

    private var realm: Realm? = null

    override fun open() {

        realm = Realm.getDefaultInstance()

    }

    override fun close() {
        checkIsRealmOpen()
        realm?.removeAllChangeListeners()
        realm?.close()
        realm = null
    }


    override fun executeTransaction(transaction: Realm.Transaction) {
        checkIsRealmOpen()
        realm?.executeTransaction(transaction)
    }

    override fun addListener(@NotNull channelInfo: MutableList<ChannelInfo>?, @NotNull listener: OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>>) {
        (channelInfo as RealmResults<ChannelInfo>).addChangeListener(listener)
    }

    override fun removeListener(@NotNull channelInfo: MutableList<ChannelInfo>?, @NotNull listener: OrderedRealmCollectionChangeListener<RealmResults<ChannelInfo>>) {
        (channelInfo as RealmResults<ChannelInfo>).removeChangeListener(listener)
    }

    override fun getAllChannelsInfo(filedName: String): RealmResults<ChannelInfo>? {
        checkIsRealmOpen()
        return realm?.where(ChannelInfo::class.java)?.findAllSorted(filedName, if (filedName.equals("isSave")) Sort.DESCENDING else Sort.ASCENDING)
    }

    override fun reset() {
        realm?.removeAllChangeListeners()
    }


    private fun checkIsRealmOpen() {
        if (realm == null)
            throw RuntimeException("Realm is not open")
    }

}