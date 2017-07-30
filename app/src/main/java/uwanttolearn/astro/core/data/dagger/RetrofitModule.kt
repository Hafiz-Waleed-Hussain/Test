package com.uwanttolearn.datamodule.dagger

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import uwanttolearn.astro.core.data.dagger.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by waleed on 24/07/2017.
 */
@Module
class RetrofitModule(val baseUrl: String) {


    @Provides
    @ApplicationScope
    fun retrofit(client: OkHttpClient) =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(client)
                    .build()


    @Provides
    @ApplicationScope
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .build()

    @Provides
    @ApplicationScope
    fun httpLoggingInterceptor() =
            HttpLoggingInterceptor({
                try {
                    var jsonObject = JSONObject(it.toString())
                    Timber.i(jsonObject.toString(4))
                } catch (e: Exception) {
                    Timber.i(it)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)

}