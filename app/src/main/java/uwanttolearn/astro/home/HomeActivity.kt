package uwanttolearn.astro.home

import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uwanttolearn.astro.core.abstracts.AstroActivity
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import timber.log.Timber
import uwanttolearn.astro.R
import uwanttolearn.astro.app.App
import uwanttolearn.astro.home.dagger.DaggerHomeActivityComponent
import uwanttolearn.astro.home.dagger.HomeActivityModule
import javax.inject.Inject

class HomeActivity : AstroActivity() {

    @Inject
    lateinit var astroRepository: AstroRepositoryDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerHomeActivityComponent.builder()
                .appComponent(App.app.appComponent)
                .homeActivityModule(HomeActivityModule())
                .build().inject(this)


        astroRepository
                .getAllChannels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { Timber.i(it.toString()) },
                        { Timber.i(it.message) }
                )


    }
}
