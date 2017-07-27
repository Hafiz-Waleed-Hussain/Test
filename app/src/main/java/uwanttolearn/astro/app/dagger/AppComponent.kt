package uwanttolearn.astro.app.dagger

import uwanttolearn.astro.core.data.dagger.ApplicationScope
import dagger.Component
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.ChannelsDataSource

/**
 * Created by waleed on 24/07/2017.
 */
@ApplicationScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun astroRepositoryDataSource(): AstroRepositoryDataSource

}