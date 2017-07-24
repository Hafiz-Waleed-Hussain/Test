package com.uwanttolearn.datamodule.dagger

import uwanttolearn.astro.core.data.dagger.ApplicationScope
import uwanttolearn.astro.core.data.AstroRepository
import com.uwanttolearn.datamodule.network.AstroAppService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by waleed on 24/07/2017.
 */
@Module(includes = arrayOf(RetrofitModule::class))
class AstroNetworkModule {

    @Provides
    @ApplicationScope
    fun astroRepositoryDataSource(astroAppService: AstroAppService) =
            AstroRepository.getInstance(astroAppService)


    @Provides
    @ApplicationScope
    fun astroAppService(retrofit: Retrofit) = retrofit.create(AstroAppService::class.java)


}