package com.example.amphibians.data

import com.example.amphibians.model.Amphibians
import com.example.amphibians.network.AmphibiansApiService

interface AmphibianRepository {
    suspend fun getAmphibian(): List<Amphibians>
}

class NetworkAmphibianRepository(
    private val retrofitApiService: AmphibiansApiService
) : AmphibianRepository {
    override suspend fun getAmphibian(): List<Amphibians> = retrofitApiService.getAmphibians()
}
