package uwanttolearn.astro.app

import android.app.Application
import uwanttolearn.astro.app.dagger.AppComponent
import uwanttolearn.astro.app.dagger.AppModule
import uwanttolearn.astro.app.dagger.DaggerAppComponent
import com.uwanttolearn.datamodule.dagger.AstroNetworkModule
import com.uwanttolearn.datamodule.dagger.RetrofitModule

/**
 * Created by waleed on 24/07/2017.
 */
class App : Application() {

    companion object {
        lateinit var app: App
            private set
    }

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        app = this
       appComponent = DaggerAppComponent.builder()
                .appModule(AppModule())
                .astroNetworkModule(AstroNetworkModule())
                .retrofitModule(RetrofitModule("http://ams-api.astro.com.my/ams/v3/"))
                .build()

    }
}