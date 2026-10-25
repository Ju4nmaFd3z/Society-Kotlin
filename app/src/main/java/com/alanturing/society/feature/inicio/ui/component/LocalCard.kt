package com.alanturing.society.feature.inicio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color



// Composable para tarjeta de cada local
@Composable
fun LocalCard(
    local: Local,
    analizarClick: (String) -> Unit,
    modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { analizarClick(local.id) }
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            if (local.hasPhotos) { // Si no devuelve null local.photos

                // Lazy Row sirve para hacer el carrusel con una distancia de 8.dp entre imagenes
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(local.photos) { url ->
                        AsyncImage( // Cada imagen del local
                            model = url,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,  // La imagen llena su caja sin recortarse
                            placeholder = ColorPainter(Color.White),
                            modifier = Modifier
                                .height(140.dp)
                                .widthIn(min = 140.dp) // Ancho mínimo mientras carga
                        )
                    }
                }
            }

            Text(
                text = local.name,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                maxLines = 1
            )

            Text(text = "${local.ratingText} (${local.userRatingCount})") // Media de reseñas y numero total de reseñas
            Text(
                text = local
                    .address,
                style = MaterialTheme
                    .typography
                    .bodySmall,
                maxLines = 2
            )
            Button (
                onClick = { analizarClick(local.id)},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Analizar")
            }
        }
    }
}

@Preview(showBackground = true, name = "Con fotos")
@Composable
private fun PreviewConFotos() {
    LocalCard(
        local = Local(
            id = "1",
            name = "Bar El Boquerón Dorado",
            address = "Calle Larios 12, Málaga",
            rating = 4.6,
            userRatingCount = 1280,
            photos = listOf(
                "https://picsum.photos/seed/boqueron1/800/600",
                "https://picsum.photos/seed/boqueron2/800/600",
                "https://picsum.photos/seed/boqueron3/800/600"
            )
        ),
        analizarClick = {}
    )
}