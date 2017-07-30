package uwanttolearn.astro.home_feature.dagger

import android.content.Context
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.Module
import dagger.Provides
import uwanttolearn.astro.core.data.ChannelsRepository
import uwanttolearn.astro.core.data.dagger.FragmentScope
import uwanttolearn.astro.core.data.dagger.IntentServiceScope
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.home_feature.HomeFragment
import uwanttolearn.astro.home_feature.HomeViewModel
import uwanttolearn.astro.home_feature.adapter.HomeAdapter

/**
 * Created by waleed on 27/07/2017.
 */
@Module
class HomeFragmentModule(val fragment: HomeFragment) {


    @Provides
    @FragmentScope
    fun viewModel(channelsRepository: ChannelsDataSource) = HomeViewModel(channelsRepository, fragment)

    @Provides
    @FragmentScope
    fun adapter() = HomeAdapter()

    @Provides
    @FragmentScope
    fun layoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(fragment.context)


    @Provides
    @FragmentScope
    fun channelsDataSource(): ChannelsDataSource = ChannelsRepository()

    @Provides
    @FragmentScope
    fun handler() = Handler()
}