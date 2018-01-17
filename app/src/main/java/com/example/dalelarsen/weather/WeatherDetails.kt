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

        var retriever = WeatherRetriever()
        val callback = object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("We got a response")
                println(response?.body()?.query?.results?.channel?.title)
                println(response?.body()?.query?.results?.channel?.item?.forecast)
                title = response?.body()?.query?.results?.channel?.title

                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()
                if (forecasts != null) {
                    for (forecast in forecasts) {
                        val forecastStr = "${forecast.date} - High: ${forecast.high} Low: ${forecast.low} - ${forecast.text}"
                        forecastStrings.add(forecastStr)
                    }
                }

                var forecastListView = findViewById<ListView>(R.id.forecastListView)
                var adapter = ArrayAdapter(this@WeatherDetails, android.R.layout.simple_list_item_1, forecastStrings)
                forecastListView.adapter = adapter
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("It failed")
                t!!.printStackTrace()
            }
        }

        val searchTerm = intent.extras.getString("searchTerm")

        retriever.getForecast(callback, searchTerm)
    }
}
