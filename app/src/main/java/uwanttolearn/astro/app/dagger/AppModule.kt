package uwanttolearn.astro.app.dagger

import com.uwanttolearn.datamodule.dagger.AstroNetworkModule
import dagger.Module
import uwanttolearn.astro.core.data.dagger.ChannelsModule

/**
 * Created by waleed on 24/07/2017.
 */
@Module(includes = arrayOf(AstroNetworkModule::class, ChannelsModule::class))
class AppModule