package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    uiState: AmphibiansUiState,
    modifier: Modifier = Modifier
) {
    when(uiState){
        is AmphibiansUiState.Success -> SuccessScreen(uiState.result)
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Error -> ErrorScreen()
    }

}

@Composable
fun SuccessScreen(
    result: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(29.dp)) {
        Text(text = result)
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

}