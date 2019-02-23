package com.pixelart.lastfmchallengemvp.remote

import com.pixelart.lastfmchallengemvp.common.END_POINT
import com.pixelart.lastfmchallengemvp.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(END_POINT)
    fun getAlbums(
        @Query("method")method: String,
        @Query("album")album: String,
        @Query("api_key")apiKey: String,
        @Query("format")format: String
    ): Single<ApiResponse>
}