package com.example.imagesearch

import com.example.mylib.openapi.kakao.image.data.ImageSearchResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoImageSearchService {

    @Headers("Authorization: KakaoAK 9fd6e9e7251cbfb5ee6c2ea8c8a8423e")
    @GET("/v2/search/image")
    fun requestSearchImage(  // Call 객체를 만드는 factory method
        @Query("query") keyword: String,
        @Query("sort") sort: String = "recency",
        @Query("page") page: Int,
        @Query("size") size: Int=10
    ): Call<ImageSearchResult>  // 실제 호출하는 객체를 리턴
}

// SingLeton: 오직한개의 인스턴스만 운영
//
object KakaoImageSerach{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): KakaoImageSearchService =
                    retrofit.create(KakaoImageSearchService::class.java)
}