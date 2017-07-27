package uwanttolearn.astro.home_feature.dagger

import dagger.Component
import uwanttolearn.astro.app.dagger.AppComponent
import uwanttolearn.astro.core.data.dagger.FragmentScope
import uwanttolearn.astro.home_feature.HomeFragment

/**
 * Created by waleed on 27/07/2017.
 */
@FragmentScope
@Component(modules = arrayOf(HomeFragmentModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeFragmentComponent {

    fun inject(fragment: HomeFragment)
}