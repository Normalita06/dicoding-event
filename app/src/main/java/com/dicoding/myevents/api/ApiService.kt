package com.dicoding.myevents.api


import com.dicoding.myevents.model.DetailResponse
import com.dicoding.myevents.model.EventResponse
import com.dicoding.myevents.model.ListEventsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    fun getEvents(@Query("active") active: Int): Call<EventResponse>

    @GET("events/{id}")
    fun getEventById(@Path("id") id: Int): Call<DetailResponse>


}
