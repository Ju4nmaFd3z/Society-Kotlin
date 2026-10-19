package com.alanturing.society.feature.inicio.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanturing.society.feature.inicio.model.Local


// composable para tarjeta de cada local.
@Composable
fun LocalCard(local: Local, modifier: Modifier = Modifier) {

    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(text = local.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${local.ratingText} (${local.userRatingCount})") // media de reseñas y numero total de reseñas.
            Text(text = local.address, style = MaterialTheme.typography.bodySmall)
        }
    }
}


