package com.uwanttolearn.datamodule.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import uwanttolearn.astro.core.data.json.ChannelLists

/**
 * Created by waleed on 24/07/2017.
 */
interface AstroAppService {

    // For scalability we should add pagination param but currently that is not the requirement :)
    @GET("getChannelList")
    fun getAllChannels(): Observable<ChannelLists>


    @GET("getChannels")
    fun getAllChannelsWithMetaData(): Observable<ChannelLists>


//    @GET("/getChannels")
//    fun getEvents(@Query"")

}