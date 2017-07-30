package uwanttolearn.astro.home.dagger

import dagger.Component
import uwanttolearn.astro.app.dagger.AppComponent
import uwanttolearn.astro.core.data.dagger.ActivityScope
import uwanttolearn.astro.home.HomeActivity

/**
 * Created by waleed on 24/07/2017.
 */
@ActivityScope
@Component(modules = arrayOf(HomeActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeActivityComponent {

    fun inject(activity: HomeActivity)
}