package uwanttolearn.astro.core.data.source.remote

import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import com.uwanttolearn.datamodule.network.AstroAppService
import io.reactivex.Observable
import uwanttolearn.astro.core.data.json.ChannelLists
import uwanttolearn.astro.core.data.json.TVGuide
import java.util.*

/**
 * Created by waleed on 24/07/2017.
 */
internal class RemoteAstroDataSource(val astroAppService: AstroAppService) : AstroRepositoryDataSource {

    override fun getAllChannelsWithMinimalData(): Observable<ChannelLists> = astroAppService.getAllChannels()

    override fun getAllChannelsWithMetaData(): Observable<ChannelLists> = astroAppService.getAllChannelsWithMetaData()

    override fun getTodayEvents(channelIds: List<String>): Observable<TVGuide> {
        val c = Calendar.getInstance()
        val today = "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.DATE)}"

        return astroAppService.getEvents(today.plus(" 00:00"),
                today.plus(" 23:59"), channelIds.joinToString(","))
    }

}