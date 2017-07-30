package uwanttolearn.astro.feature_home

import io.realm.RealmResults
import uwanttolearn.astro.core.data.pojos.ChannelInfo

/**
 * Created by waleed on 27/07/2017.
 */
interface HomeFragmentContract {
    fun addData(allChannelsInfo: RealmResults<ChannelInfo>?)
    fun notifyDataChanged()
    fun resetAdapter()
    fun updateRowOnPosition(position: Int)
}