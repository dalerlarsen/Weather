package com.example.dalelarsen.weather

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getForecastButton: Button = findViewById(R.id.get_forecast_button)
        getForecastButton.setOnClickListener {
            var weatherDetailsIntent: Intent = Intent(this, WeatherDetails::class.java)
            startActivity(weatherDetailsIntent)
        }

    }
}
