package com.uwanttolearn.datamodule.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by waleed on 24/07/2017.
 */
interface AstroAppService {

    // For scalability we should add pagination param but currently that is not the requirement :)
    @GET("/getChannelList")
    fun getAllChannels(): Observable<Any>
//
//
//    @GET("/getChannels")
//    fun getAllChannelsWithMetaData(@Query("channelId") string: String? = null): Observable<T>


//    @GET("/getChannels")
//    fun getEvents(@Query"")

}