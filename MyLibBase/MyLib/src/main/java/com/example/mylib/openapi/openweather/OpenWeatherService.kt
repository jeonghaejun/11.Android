package com.example.mylib.openapi.openweather


import android.util.Log
import com.example.mylib.openapi.openweather.data.Weather
import com.example.mylib.openapi.piapi.OpenApi
import com.example.mylib.openapi.openweather.data.WeatherCast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="a0f1e28151d0e65e1e9f18c14354a4e0"
interface OpenWeatherService {
    // http://api.openweathermap.org/data/2.5/weather?q=Seoul&APPID=a0f1e28151d0e65e1e9f18c14354a4e0&lang=kr
    @GET("/data/2.5/weather")
    fun qetWeatherCast(
        @Query("q") city: String,
        @Query("APPID") apikey: String= API_KEY,
        @Query("lang") lang: String="kr"
    ): Call<WeatherCast>
}

object OpenWeather : OpenApi(){
    override val TAG = javaClass.simpleName
    override val BASE_URL = "http://api.openweathermap.org"

    private val service = retrofit.create(OpenWeatherService::class.java)

    fun getWeatherCast(city: String,callback:(WeatherCast)->Unit){
        service.qetWeatherCast(city)
                .enqueue(ApiCallback<WeatherCast>(callback))
    }
}

//object OpenWeather{
//    val TAG = javaClass.simpleName
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://api.openweathermap.org")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    fun getService(): OpenWeatherService = retrofit.create(
//            OpenWeatherService::class.java)
//
//    fun getWeatherCast(city:String,callback: (WeatherCast)->Unit) {
//        getService()
//            .qetWeatherCast(city)
//            .enqueue(object : Callback<WeatherCast> {
//                override fun onFailure(call: Call<WeatherCast>, t: Throwable) {
//                    Log.e(TAG,t.toString())
//                }
//
//                override fun onResponse(
//                    call: Call<WeatherCast>,
//                    response: Response<WeatherCast>
//                ) {
//                    if (response.isSuccessful){
//                        val result = response.body()   // body()의 리턴 타입 ImageSearchResult?
//                        callback(result!!)
//                    } else {
//                        Log.w(TAG,"${response.code()},${response.message()}")
//                        Log.w(TAG,"${response.toString()}")
//                    }
//                }
//            })
//    }
//}