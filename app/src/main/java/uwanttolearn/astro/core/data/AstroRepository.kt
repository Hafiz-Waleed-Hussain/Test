package uwanttolearn.astro.core.data

import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.remote.RemoteAstroDataSource
import com.uwanttolearn.datamodule.network.AstroAppService
import io.reactivex.Observable

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


    override fun getAllChannels(): Observable<Any> = remoteAstroDataSource.getAllChannels()


}