package com.example.dalelarsen.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)

        var forecastListView = findViewById<ListView>(R.id.forecastListView)
        var randomThings = listOf<String>("Hello", "How are you?", "I like cheese", "How are you?", "I like cheese", "How are you?", "I like cheese", "How are you?", "I like cheese", "How are you?", "I like cheese", "How are you?", "I like cheese", "How are you?", "I like cheese")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, randomThings)

        forecastListView.adapter = adapter

        var retriever = WeatherRetriever()
        val callback = object : Callback<List<Forecast>> {
            override fun onResponse(call: Call<List<Forecast>>?, response: Response<List<Forecast>>?) {
                println("We got a response")
                println(response?.body())
                for (forecast : Forecast in response!!.body()!!) {
                    println("High: ${forecast.high}, Low: ${forecast.low}")
                }
            }

            override fun onFailure(call: Call<List<Forecast>>?, t: Throwable?) {
                println("It failed")
                t!!.printStackTrace()
            }
        }

        retriever.getForecast(callback)
    }
}
