package com.alanturing.society.feature.empezamos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alanturing.society.R
import com.alanturing.society.feature.empezamos.ui.component.BuscadorLocales
import com.alanturing.society.feature.empezamos.ui.component.LocalCard

/**
 * Pantalla 02 · Empezamos («Busca tu local»): buscador arriba y, debajo, según el
 * estado, un mensaje, la carga, el error con Reintentar o la lista de locales, una
 * LocalCard por local. Analizar lleva a 03 · Análisis y el enlace «Añádelo a mano»
 * a S02M · Alta manual.
 */
@Composable
fun EmpezamosScreen(
    estado: EmpezamosUiState,
    onBuscar: (String) -> Unit,
    onReintentar: () -> Unit,
    onAnalizar: (String) -> Unit,
    onAltaManual: () -> Unit,
    modifier: Modifier = Modifier
) {
    var texto by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        BuscadorLocales(
            texto = texto,
            cambioTexto = { texto = it },
            buscar = onBuscar,
            modifier = Modifier.padding(16.dp)
        )

        TextButton(
            onClick = onAltaManual,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(stringResource(R.string.empezamos_alta_manual))
        }

        // Debajo del buscador se ve una cosa u otra según el estado
        val centrado = Modifier
            .fillMaxWidth()
            .padding(16.dp)

        when (estado) {
            EmpezamosUiState.Inicial -> Text(
                stringResource(R.string.empezamos_estado_inicial),
                modifier = centrado
            )

            EmpezamosUiState.Cargando -> CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            EmpezamosUiState.Vacio -> Text(
                stringResource(R.string.empezamos_estado_vacio),
                modifier = centrado
            )

            EmpezamosUiState.Error -> Column(
                modifier = centrado,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.empezamos_estado_error))
                Button(onClick = onReintentar) {
                    Text(stringResource(R.string.empezamos_reintentar))
                }
            }

            is EmpezamosUiState.ConDatos -> LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(estado.locales, key = { it.id }) { local ->
                    LocalCard(local = local, analizarClick = onAnalizar)
                }
            }
        }
    }
}
