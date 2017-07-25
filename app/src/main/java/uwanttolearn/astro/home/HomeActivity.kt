package uwanttolearn.astro.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
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
import uwanttolearn.astro.home.favourites.FavouritesFragment
import uwanttolearn.astro.home_feature.HomeFragment
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

        replaceFragment(HomeFragment())

        HomeActivity_bottom_navigation_view.setOnNavigationItemSelectedListener {
            var fragment = when (it.itemId) {
                R.id.home_tab -> HomeFragment()
                R.id.tv_guide_tab -> FavouritesFragment()
                R.id.favourite_tab -> HomeFragment()
                else -> Fragment()
            }

            replaceFragment(fragment)
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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.HomeActivity_frame_layout, fragment)
                .commit()
    }
}
