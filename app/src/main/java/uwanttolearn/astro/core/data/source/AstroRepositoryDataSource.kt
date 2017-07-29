package uwanttolearn.astro.core.data.source

import io.reactivex.Observable
import uwanttolearn.astro.core.data.json.ChannelLists
import uwanttolearn.astro.core.data.json.TVGuide

/**
 * Created by waleed on 24/07/2017.
 */
interface AstroRepositoryDataSource {

    fun getAllChannelsWithMinimalData(): Observable<ChannelLists>

    fun getAllChannelsWithMetaData(): Observable<ChannelLists>

    fun getTodayEvents(channelIds: List<String>): Observable<TVGuide>
}