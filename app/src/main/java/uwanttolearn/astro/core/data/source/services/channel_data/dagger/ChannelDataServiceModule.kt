package uwanttolearn.astro.core.data.source.services.channel_data.dagger

import dagger.Module
import dagger.Provides
import uwanttolearn.astro.core.data.ChannelsRepository
import uwanttolearn.astro.core.data.dagger.IntentServiceScope
import uwanttolearn.astro.core.data.source.ChannelsDataSource

/**
 * Created by waleed on 26/07/2017.
 */
@Module
class ChannelDataServiceModule {

    @Provides
    @IntentServiceScope
    fun channelsDataSource(): ChannelsDataSource = ChannelsRepository()
}