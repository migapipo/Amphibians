package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.Application
import com.example.amphibians.data.AmphibianRepository
import com.example.amphibians.model.Amphibians
import kotlinx.coroutines.launch
import retrofit2.HttpException


sealed interface AmphibiansUiState {
    data class Success(val result: List<Amphibians>): AmphibiansUiState
    object Loading: AmphibiansUiState
    object Error: AmphibiansUiState


}

class AmphibiansViewModel(private val amphibianRepository: AmphibianRepository): ViewModel() {

    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

     fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = try {
                val result = amphibianRepository.getAmphibian()
                AmphibiansUiState.Success(
                    result
//                    "Success: ${result.size} amphibians retrieved"
                )
            }catch (e: java.io.IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val amphibianRepository = application.container.amphibianRepository
                AmphibiansViewModel(amphibianRepository)
            }
        }
    }
}
