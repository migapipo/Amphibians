package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch
import retrofit2.HttpException


sealed interface AmphibiansUiState {
    data class Success(val result: String): AmphibiansUiState
    object Loading: AmphibiansUiState
    object Error: AmphibiansUiState


}

class AmphibiansViewModel: ViewModel() {

    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = try {
                val result = AmphibiansApi.retrofitService.getAmphibians()
                AmphibiansUiState.Success(
                    "Success: ${result.size} amphibians retrieved"
                )
            }catch (e: java.io.IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }
}