package uwanttolearn.astro.core.data.source.remote

import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import com.uwanttolearn.datamodule.network.AstroAppService
import io.reactivex.Observable
import uwanttolearn.astro.core.data.json.ChannelLists

/**
 * Created by waleed on 24/07/2017.
 */
internal class RemoteAstroDataSource(val astroAppService: AstroAppService) : AstroRepositoryDataSource {

    override fun getAllChannelsWithMinimalData(): Observable<ChannelLists> = astroAppService.getAllChannels()

    override fun getAllChannelsWithMetaData(): Observable<ChannelLists> = astroAppService.getAllChannelsWithMetaData()

}