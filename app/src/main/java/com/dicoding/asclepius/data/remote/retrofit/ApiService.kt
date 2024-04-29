package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "cancer",
        @Query("apiKey") apiKey: String = "c9bfd5ee9cab45f4b03e3aa7aa508487"
    ): NewsResponse
}