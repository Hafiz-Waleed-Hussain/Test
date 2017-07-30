package uwanttolearn.astro.feature_home.dagger

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.Module
import dagger.Provides
import uwanttolearn.astro.core.data.ChannelsRepository
import uwanttolearn.astro.core.data.dagger.FragmentScope
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.feature_home.HomeFragment
import uwanttolearn.astro.feature_home.HomeViewModel
import uwanttolearn.astro.feature_home.adapter.HomeAdapter

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