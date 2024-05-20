package com.example.amphibians.network

import com.example.amphibians.model.Amphibians
import retrofit2.http.GET

/**
 * Retrofit service object for creating api calls
 */
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibians>
}
