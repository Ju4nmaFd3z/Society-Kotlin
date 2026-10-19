package com.alanturing.society.feature.inicio.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alanturing.society.feature.inicio.model.Local
import androidx.compose.foundation.lazy.items


// composable para tarjeta de cada local.
@Composable
fun LocalCard(local: Local, modifier: Modifier = Modifier) {

    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Lazy Row sirve para hacer el carrusel con una distancia de 8.dp entre imagenes.
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(local.photos) { url ->
                    AsyncImage( // cada imagen del local
                        model = url,
                        contentDescription = null,
                        modifier = Modifier.wrapContentWidth().height(140.dp) // wrapContentWidht para que ocupe el ancho de su propia imagen
                    )
                }
            }

            Text(text = local.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "${local.ratingText} (${local.userRatingCount})") // media de reseñas y numero total de reseñas.
            Text(text = local.address, style = MaterialTheme.typography.bodySmall)
        }
    }
}


