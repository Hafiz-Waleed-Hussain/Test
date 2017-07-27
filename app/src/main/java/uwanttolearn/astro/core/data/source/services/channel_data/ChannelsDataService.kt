package uwanttolearn.astro.core.data.source.services.channel_data

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import io.realm.Realm
import timber.log.Timber
import uwanttolearn.astro.app.App
import uwanttolearn.astro.core.data.pojos.ChannelInfo
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.core.data.source.services.channel_data.dagger.DaggerChannlesDataServiceComponent
import javax.inject.Inject

/**
 * Created by waleed on 26/07/2017.
 */
class ChannelsDataService : IntentService("ChannelsDataService") {

    @Inject
    lateinit var astroRepository: AstroRepositoryDataSource
    @Inject
    lateinit var channelsRepository: ChannelsDataSource

    private val handler = Handler()

    override fun onCreate() {
        super.onCreate()

        DaggerChannlesDataServiceComponent.builder()
                .appComponent(App.app.appComponent)
                .build().inject(this)
        channelsRepository.open()
    }

    override fun onHandleIntent(p0: Intent?) {

        astroRepository
                .getAllChannelsWithMinimalData()
                .subscribe(
                        {
                            val data = arrayListOf<ChannelInfo>()
                            it.minimalChannelsInfo.map { ChannelInfo(it.channelId, it.channelTitle, it.channelStbNumber.toInt()) }
                                    .forEach { channelInfo ->
                                        Timber.i(Thread.currentThread().name)
                                        data.add(channelInfo)
                                    }
                            handler.post {
                                channelsRepository.executeTransaction(Realm.Transaction {
                                    it.copyToRealmOrUpdate(data)
                                    Timber.i(Thread.currentThread().name)
                                })
                            }
                        },
                        { Timber.i(it.message) }
                )


        astroRepository.getAllChannelsWithMetaData()
                .subscribe({
                    val data = arrayListOf<ChannelInfo>()
                    it.channel.map { ChannelInfo(it.channelId, it.channelTitle, it.channelStbNumber.toInt(), it.channelExtRef[1].value) }
                            .forEach { channelInfo ->
                                Timber.i(Thread.currentThread().name)
                                data.add(channelInfo)
                            }

                    handler.post {
                        channelsRepository.executeTransaction(Realm.Transaction {
                            it.copyToRealmOrUpdate(data)
                            Timber.i(Thread.currentThread().name)
                        })
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        channelsRepository.close()
    }
}