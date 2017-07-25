package uwanttolearn.astro.home

import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import uwanttolearn.astro.core.abstracts.AstroActivity
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import timber.log.Timber
import uwanttolearn.astro.R
import uwanttolearn.astro.app.App
import uwanttolearn.astro.home.dagger.DaggerHomeActivityComponent
import uwanttolearn.astro.home.dagger.HomeActivityModule
import uwanttolearn.astro.favourites.FavouritesFragment
import uwanttolearn.astro.home.adapter.HomeViewPagerAdapter
import uwanttolearn.astro.home_feature.HomeFragment
import javax.inject.Inject

class HomeActivity : AstroActivity() {

    @Inject
    lateinit var astroRepository: AstroRepositoryDataSource
    @Inject
    lateinit var pagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerHomeActivityComponent.builder()
                .appComponent(App.app.appComponent)
                .homeActivityModule(HomeActivityModule(this, supportFragmentManager))
                .build().inject(this)


        HomeActivity_view_pager.offscreenPageLimit = 3
        HomeActivity_view_pager.adapter = pagerAdapter

        HomeActivity_bottom_navigation_view.setOnNavigationItemSelectedListener {
            HomeActivity_view_pager.setCurrentItem(when (it.itemId) {
                R.id.home_tab -> 0
                R.id.tv_guide_tab -> 1
                R.id.favourite_tab -> 2
                else -> 0
            }, true)

            true
        }

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
