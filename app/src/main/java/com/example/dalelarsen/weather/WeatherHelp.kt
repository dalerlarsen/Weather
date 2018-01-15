package com.example.dalelarsen.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by dalelarsen on 1/10/18.
 * */

interface WeatherAPI {
    @GET("bins/13pj0x")
    fun getForecast() : Call<List<Forecast>>
}

class Forecast(val high: String, val low: String)

class WeatherRetriever {
    val service: WeatherAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<List<Forecast>>) {
        val call = service.getForecast()
        call.enqueue(callback)
    }
}