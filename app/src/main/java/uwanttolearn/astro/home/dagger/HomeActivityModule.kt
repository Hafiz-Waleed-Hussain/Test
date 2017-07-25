package uwanttolearn.astro.home.dagger

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.dagger.ActivityScope
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.favourites.FavouritesFragment
import uwanttolearn.astro.home.HomeActivity
import uwanttolearn.astro.home.adapter.HomeViewPagerAdapter
import uwanttolearn.astro.home_feature.HomeFragment

/**
 * Created by waleed on 24/07/2017.
 */
@Module
class HomeActivityModule(val context: Context, val fm: FragmentManager) {

    @Provides
    @ActivityScope
    fun pagerAdapter(context: Context, fm: FragmentManager): HomeViewPagerAdapter {
        return HomeViewPagerAdapter(context, fm, listOf<Pair<Fragment, Int>>(
                Pair(HomeFragment(), R.string.home),
                Pair(FavouritesFragment(), R.string.tv_guide),
                Pair(FavouritesFragment(), R.string.favourite))
        )
    }

    @Provides
    @ActivityScope
    fun context() = context

    @Provides
    @ActivityScope
    fun fragmentManager() = fm


}