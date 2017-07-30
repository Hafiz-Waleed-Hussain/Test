package uwanttolearn.astro.feature_tv_guide.dagger

import dagger.Component
import uwanttolearn.astro.app.dagger.AppComponent
import uwanttolearn.astro.core.data.dagger.FragmentScope
import uwanttolearn.astro.favourites.TVGuideFragment

/**
 * Created by waleed on 29/07/2017.
 */
@FragmentScope
@Component(modules = arrayOf(TVGuideFragmentModule::class), dependencies = arrayOf(AppComponent::class))
interface TVGuideFragmentComponent {

    fun inject(fragment: TVGuideFragment)
}