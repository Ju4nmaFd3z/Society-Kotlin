package com.alanturing.society.feature.inicio.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.alanturing.society.R
import com.alanturing.society.feature.inicio.model.Local
import com.alanturing.society.feature.inicio.ui.component.LocalCard
import com.alanturing.society.ui.theme.SocietyTheme

/**
 * Pantalla 02 · Empezamos («Busca tu local»), hecha en Compose. Muestra el buscador y,
 * debajo, según el estado: un mensaje, un indicador de carga, el aviso de error con
 * reintento o la lista de locales con carga perezosa, una LocalCard por local.
 * Al pulsar Analizar en una tarjeta se navega a 03 · Análisis (onAnalizar).
 * Todavía no busca ni carga nada, y el texto del buscador se guarda aquí hasta que
 * lo asuma el ViewModel. La carpeta se llama feature/inicio por historia: en el Figma
 * esta pantalla es la 02, y la 01 · Inicio es la bienvenida.
 * Pendiente: buscador de página web (ver nota de diseño). El Figma actual no lo muestra.
 */
@Composable
fun InicioScreen(
    estado: InicioUiState,
    modifier: Modifier = Modifier,
    onBuscar: (String) -> Unit = {},
    onReintentar: () -> Unit = {},
    onAnalizar: (String) -> Unit = {}
) {
    var consulta by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = consulta,
            onValueChange = { consulta = it },
            label = { Text(stringResource(R.string.inicio_buscador)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onBuscar(consulta) }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        when (estado) {
            InicioUiState.Inicial -> Centrado(Modifier.weight(1f)) {
                Text(stringResource(R.string.inicio_estado_inicial))
            }

            InicioUiState.Cargando -> Centrado(Modifier.weight(1f)) {
                CircularProgressIndicator()
            }

            InicioUiState.Vacio -> Centrado(Modifier.weight(1f)) {
                Text(stringResource(R.string.inicio_estado_vacio))
            }

            InicioUiState.Error -> Centrado(Modifier.weight(1f)) {
                Text(stringResource(R.string.inicio_estado_error))
                Button(onClick = onReintentar) {
                    Text(stringResource(R.string.inicio_reintentar))
                }
            }

            is InicioUiState.ConDatos -> LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(estado.locales, key = { it.id }) { local ->
                    LocalCard(local = local, analizarClick = onAnalizar)
                }
            }
        }
    }
}

// Columna centrada que ocupa el espacio que deja el buscador, para los mensajes
@Composable
private fun Centrado(modifier: Modifier, contenido: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = contenido
    )
}

// Datos de ejemplo solo para las previews. El tercero no tiene fotos
private val localesDeEjemplo = listOf(
    Local(
        id = "loc_001",
        name = "Bar El Boquerón Dorado",
        address = "Calle Larios 12, Málaga",
        rating = 4.6,
        userRatingCount = 1280,
        photos = listOf("https://picsum.photos/seed/boqueron1/800/600")
    ),
    Local(
        id = "loc_002",
        name = "Asador La Brasa",
        address = "Calle Granada 34, Málaga",
        rating = 4.4,
        userRatingCount = 860,
        photos = listOf("https://picsum.photos/seed/brasa1/800/600")
    ),
    Local(
        id = "loc_007",
        name = "Restaurante Sabor Sur",
        address = "Calle Mármoles 3, Málaga",
        rating = 3.9,
        userRatingCount = 214,
        photos = emptyList()
    )
)

// Pasa a la preview cada uno de los cinco estados
class EstadosPreviewProvider : PreviewParameterProvider<InicioUiState> {
    override val values: Sequence<InicioUiState> = sequenceOf(
        InicioUiState.Inicial,
        InicioUiState.Cargando,
        InicioUiState.Vacio,
        InicioUiState.Error,
        InicioUiState.ConDatos(localesDeEjemplo)
    )
}

@Preview(showBackground = true)
@Composable
private fun InicioScreenPreview(
    @PreviewParameter(EstadosPreviewProvider::class) estado: InicioUiState
) {
    SocietyTheme {
        InicioScreen(estado = estado)
    }
}