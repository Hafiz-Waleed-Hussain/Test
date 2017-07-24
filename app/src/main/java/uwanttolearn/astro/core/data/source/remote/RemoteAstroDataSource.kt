package uwanttolearn.astro.core.data.source.remote

import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import com.uwanttolearn.datamodule.network.AstroAppService
import io.reactivex.Observable

/**
 * Created by waleed on 24/07/2017.
 */
internal class RemoteAstroDataSource(val astroAppService: AstroAppService) : AstroRepositoryDataSource {

    override fun getAllChannels(): Observable<Any> = astroAppService.getAllChannels()


}