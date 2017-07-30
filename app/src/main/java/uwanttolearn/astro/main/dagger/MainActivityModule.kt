package uwanttolearn.astro.main.dagger

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import uwanttolearn.astro.R
import uwanttolearn.astro.core.data.dagger.ActivityScope
import uwanttolearn.astro.favourites.TVGuideFragment
import uwanttolearn.astro.feature_home.HomeFragment
import uwanttolearn.astro.main.adapter.HomeViewPagerAdapter

/**
 * Created by waleed on 24/07/2017.
 */
@Module
class MainActivityModule(val context: Context, val fm: FragmentManager) {

    @Provides
    @ActivityScope
    fun pagerAdapter(context: Context, fm: FragmentManager): HomeViewPagerAdapter {
        return HomeViewPagerAdapter(context, fm, listOf<Pair<Fragment, Int>>(
                Pair(HomeFragment(), R.string.home),
                Pair(TVGuideFragment(), R.string.tv_guide))
        )
    }

    @Provides
    @ActivityScope
    fun context() = context

    @Provides
    @ActivityScope
    fun fragmentManager() = fm


}