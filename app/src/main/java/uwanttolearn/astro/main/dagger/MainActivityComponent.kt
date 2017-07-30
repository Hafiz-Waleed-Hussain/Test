package uwanttolearn.astro.main.dagger

import dagger.Component
import uwanttolearn.astro.app.dagger.AppComponent
import uwanttolearn.astro.core.data.dagger.ActivityScope
import uwanttolearn.astro.main.MainActivity

/**
 * Created by waleed on 24/07/2017.
 */
@ActivityScope
@Component(modules = arrayOf(MainActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface MainActivityComponent {

    fun inject(activity: MainActivity)
}