package uwanttolearn.astro.feature_tv_guide.dagger

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.Module
import dagger.Provides
import uwanttolearn.astro.core.data.ChannelsRepository
import uwanttolearn.astro.core.data.dagger.FragmentScope
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.core.data.source.ChannelsDataSource
import uwanttolearn.astro.favourites.TVGuideFragment
import uwanttolearn.astro.feature_tv_guide.TVGuideFragmentContract
import uwanttolearn.astro.feature_tv_guide.TVGuideFragmentViewModel
import uwanttolearn.astro.feature_tv_guide.adapter.TVGuideAdapter

/**
 * Created by waleed on 29/07/2017.
 */
@Module
class TVGuideFragmentModule(val fragment: TVGuideFragment) {

    @FragmentScope
    @Provides
    fun viewContract(): TVGuideFragmentContract = fragment


    @FragmentScope
    @Provides
    fun viewModel(astroRespository: AstroRepositoryDataSource, channelsDataSource: ChannelsDataSource,
                  contract: TVGuideFragmentContract) =
            TVGuideFragmentViewModel(astroRespository,channelsDataSource, contract)

    @FragmentScope
    @Provides
    fun adapter() = TVGuideAdapter()

    @FragmentScope
    @Provides
    fun layoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    @FragmentScope
    fun channelsDataSource(): ChannelsDataSource = ChannelsRepository()
}