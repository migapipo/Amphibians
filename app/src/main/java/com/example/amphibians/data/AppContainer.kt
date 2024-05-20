package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibianRepository: AmphibianRepository
}

// Container of objects shared across the whole app
class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
    override val amphibianRepository: AmphibianRepository by lazy {
        NetworkAmphibianRepository(retrofitService)
    }
}
