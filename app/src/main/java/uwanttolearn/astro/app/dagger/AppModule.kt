package uwanttolearn.astro.app.dagger

import com.uwanttolearn.datamodule.dagger.AstroNetworkModule
import dagger.Module
import dagger.Provides
import uwanttolearn.astro.core.data.AstroRepository
import uwanttolearn.astro.core.data.dagger.ApplicationScope
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource

/**
 * Created by waleed on 24/07/2017.
 */
@Module(includes = arrayOf(AstroNetworkModule::class))
class AppModule {



}