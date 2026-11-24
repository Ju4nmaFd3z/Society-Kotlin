package com.alanturing.society.feature.empezamos.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alanturing.society.R
import com.alanturing.society.feature.empezamos.model.Local
import com.alanturing.society.feature.empezamos.model.PlacePhoto
import com.alanturing.society.ui.theme.GrisTexto
import com.alanturing.society.ui.theme.Piedra

// Composable para tarjeta de cada local
@Composable
fun LocalCard(
    local: Local,
    photoUrl: String?, // null mientras llega o si el local no tiene fotos
    esMiLocalClick: (Local) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {

            if (local.hasPhotos) {
                // Mientras llega la URL se ve el fondo gris, así la tarjeta no da saltos
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // Llena la caja aunque recorte un poco la foto
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Piedra)
                )
            }

            Text(
                text = local.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = local.address,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )

            // Media de reseñas y número total de reseñas
            Text(
                text = local.ratingText?.let { "★ $it (${local.userRatingCount})" }
                    ?: stringResource(R.string.local_sin_resenas),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AtribucionGoogle(local = local, modifier = Modifier.weight(1f))
                Button(onClick = { esMiLocalClick(local) }) {
                    Text(stringResource(R.string.local_es_mi_local))
                }
            }
        }
    }
}

// Autor de la foto y «Google Maps»: las políticas de Places obligan a mostrarlos
@Composable
private fun AtribucionGoogle(local: Local, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val autores = local.photos.firstOrNull()?.authors.orEmpty()

    Column(modifier = modifier) {
        if (autores.isNotEmpty()) {
            Text(
                text = stringResource(R.string.local_foto_de, autores.joinToString()),
                style = MaterialTheme.typography.labelSmall,
                color = GrisTexto,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        // Si hay enlace, abre el local en Google Maps
        val mapsUri = local.googleMapsUri
        Text(
            text = stringResource(R.string.local_google_maps),
            style = MaterialTheme.typography.labelSmall,
            color = GrisTexto,
            textDecoration = if (mapsUri != null) TextDecoration.Underline else null,
            modifier = if (mapsUri != null) Modifier.clickable { uriHandler.openUri(mapsUri) } else Modifier
        )
    }
}

@Preview(showBackground = true, name = "Con foto")
@Composable
private fun PreviewConFoto() {
    LocalCard(
        local = Local(
            id = "1",
            name = "Bar El Boquerón Dorado",
            address = "Calle Larios 12, Málaga",
            rating = 4.6,
            userRatingCount = 1280,
            photos = listOf(PlacePhoto(name = "places/1/photos/1", authors = listOf("Ana López"))),
            googleMapsUri = "https://maps.google.com/?cid=1"
        ),
        photoUrl = "https://picsum.photos/seed/boqueron1/800/600",
        esMiLocalClick = {}
    )
}

@Preview(showBackground = true, name = "Sin foto ni reseñas")
@Composable
private fun PreviewSinFoto() {
    LocalCard(
        local = Local(
            id = "2",
            name = "Bar El Boquerón Dorado",
            address = "Calle Larios 12, Málaga",
            rating = 0.0,
            userRatingCount = 0,
            photos = emptyList(),
            googleMapsUri = null
        ),
        photoUrl = null,
        esMiLocalClick = {}
    )
}
