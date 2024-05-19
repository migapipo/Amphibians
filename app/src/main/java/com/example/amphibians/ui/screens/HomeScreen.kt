package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.amphibians.R
import com.example.amphibians.data.Amphibians

@Composable
fun HomeScreen(
    uiState: AmphibiansUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is AmphibiansUiState.Success -> SuccessScreen(uiState.result)
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Error -> ErrorScreen()
    }

}

@Composable
fun SuccessScreen(
    result: List<Amphibians>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(20.dp),
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            textAlign = TextAlign.Left
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(items = result) { amphibian ->
                AmphibianCard(amphibian)
            }
        }
    }

//    Column(
//        modifier = modifier
//            .padding(29.dp)
//            .verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        for (amphibian in result) {
//            Text(
//                text = "${amphibian.name}(${amphibian.type})",
//                fontSize = MaterialTheme.typography.titleLarge.fontSize
//            )
//            AsyncImage(
//                model = amphibian.imgSrc,
//                contentDescription = "Amphibian image",
//                error = painterResource(id = R.drawable.ic_broken_image),
//                placeholder = painterResource(id = R.drawable.loading_img)
//            )
//            Text(text = amphibian.description)
//        }
//    }
}

@Composable
private fun AmphibianCard(amphibian: Amphibians) {
    Card(
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "${amphibian.name}(${amphibian.type})",
                modifier = Modifier.padding(12.dp),
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            AsyncImage(
                model = amphibian.imgSrc,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Amphibian image",
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
            Text(text = amphibian.description,
                modifier = Modifier.padding(12.dp),)
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

}