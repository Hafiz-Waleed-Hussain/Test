package uwanttolearn.astro.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import uwanttolearn.astro.R
import uwanttolearn.astro.app.App
import uwanttolearn.astro.core.abstracts.AstroActivity
import uwanttolearn.astro.core.data.source.AstroRepositoryDataSource
import uwanttolearn.astro.main.adapter.HomeViewPagerAdapter
import uwanttolearn.astro.main.dagger.DaggerMainActivityComponent
import uwanttolearn.astro.main.dagger.MainActivityModule
import javax.inject.Inject

class MainActivity : AstroActivity() {

    @Inject
    lateinit var astroRepository: AstroRepositoryDataSource
    @Inject
    lateinit var pagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.builder()
                .appComponent(App.app.appComponent)
                .mainActivityModule(MainActivityModule(this, supportFragmentManager))
                .build().inject(this)


        HomeActivity_view_pager.offscreenPageLimit = 2
        HomeActivity_view_pager.adapter = pagerAdapter

        HomeActivity_bottom_navigation_view.setOnNavigationItemSelectedListener {
            HomeActivity_view_pager.setCurrentItem(when (it.itemId) {
                R.id.home_tab -> 0
                R.id.tv_guide_tab -> 1
                else -> 0
            }, true)
            true
        }
    }

    override fun onInternetConnected() {
    }

    override fun onInternetDisconnected() {
    }


}
