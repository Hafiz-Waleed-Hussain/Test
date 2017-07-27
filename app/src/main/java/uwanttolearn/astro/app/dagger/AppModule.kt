package uwanttolearn.astro.app.dagger

import com.uwanttolearn.datamodule.dagger.AstroNetworkModule
import dagger.Module

/**
 * Created by waleed on 24/07/2017.
 */
@Module(includes = arrayOf(AstroNetworkModule::class))
class AppModule