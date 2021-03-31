package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.mylib.openapi.openweather.OpenWeather
import com.example.mylib.openapi.openweather.data.WeatherCast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OpenWeather.getWeatherCast("Seoul"){
            Log.d("Weather",it.toString())
            textDescription.text = it.weather[0].description
            val icon = it.weather[0].icon
            val iconPath = "http://openweathermap.org/img/w/${icon}.png"
            Glide.with(this).load(iconPath).into(imgWeather)
            // 온도(섭씨)를 포함해서 기타 정보 출력...
            val temp = it.main.temp-273.15
            txtWeatherInfo.text = "온도: ${"%.1f".format(temp)}°C, 습도: ${it.main.humidity}%"
        }
    }
}