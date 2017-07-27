package uwanttolearn.astro.core.data

import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.remote.RemoteAstroDataSource
import com.uwanttolearn.datamodule.network.AstroAppService
import io.reactivex.Observable
import uwanttolearn.astro.core.data.json.ChannelLists

/**
 * Created by waleed on 24/07/2017.
 */
object AstroRepository : AstroRepositoryDataSource {
    private lateinit var remoteAstroDataSource: AstroRepositoryDataSource
    private var astroRepository: AstroRepository? = null

    // This method is not thread safe.
    fun getInstance(astroAppService: AstroAppService): AstroRepositoryDataSource {
        if (astroRepository == null) {
            astroRepository = this
            remoteAstroDataSource = RemoteAstroDataSource(astroAppService)
        }
        return astroRepository!!
    }


    override fun getAllChannelsWithMinimalData(): Observable<ChannelLists> = remoteAstroDataSource.getAllChannelsWithMinimalData()

    override fun getAllChannelsWithMetaData(): Observable<ChannelLists> = remoteAstroDataSource.getAllChannelsWithMetaData()


}