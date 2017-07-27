package uwanttolearn.astro.core.data.source.services.channel_data.dagger

import dagger.Component
import uwanttolearn.astro.app.dagger.AppComponent
import uwanttolearn.astro.core.data.dagger.IntentServiceScope
import uwanttolearn.astro.core.data.source.services.channel_data.ChannelsDataService

/**
 * Created by waleed on 26/07/2017.
 */
@IntentServiceScope
@Component(modules = arrayOf(ChannelDataServiceModule::class), dependencies = arrayOf(AppComponent::class))
interface ChannlesDataServiceComponent {
    fun inject(channelsDataService: ChannelsDataService)
}