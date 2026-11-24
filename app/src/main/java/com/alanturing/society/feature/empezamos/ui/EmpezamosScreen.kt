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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alanturing.society.R
import com.alanturing.society.feature.empezamos.model.Local
import com.alanturing.society.feature.empezamos.ui.component.BuscadorLocales
import com.alanturing.society.feature.empezamos.ui.component.ConfirmPlaceDialog
import com.alanturing.society.feature.empezamos.ui.component.LocalCard
import com.alanturing.society.ui.theme.GrisTexto

/**
 * Pantalla 02 · Empezamos («Busca tu local»): buscador arriba y, debajo, según el
 * estado, un mensaje, la carga, el error con Reintentar o la lista de locales, una
 * LocalCard por local. «Es mi local» abre el modal M02, que da de alta el local y
 * lleva a 03 · Análisis. El enlace «Añádelo a mano» lleva a S02M · Alta manual.
 */
@Composable
fun EmpezamosScreen(
    estado: EmpezamosUiState,
    confirm: ConfirmPlaceState?,
    onBuscar: (String) -> Unit,
    onReintentar: () -> Unit,
    onVerMas: () -> Unit,
    onEsMiLocal: (Local) -> Unit,
    onConfirmarLocal: () -> Unit,
    onCerrarModal: () -> Unit,
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

            is EmpezamosUiState.ConDatos -> ListaLocales(
                estado = estado,
                onEsMiLocal = onEsMiLocal,
                onVerMas = onVerMas
            )
        }
    }

    // El modal va por encima de la pantalla, solo mientras haya un local elegido
    confirm?.let {
        ConfirmPlaceDialog(
            estado = it,
            onConfirmar = onConfirmarLocal,
            onCerrar = onCerrarModal
        )
    }
}

@Composable
private fun ListaLocales(
    estado: EmpezamosUiState.ConDatos,
    onEsMiLocal: (Local) -> Unit,
    onVerMas: () -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = pluralStringResource(R.plurals.empezamos_resumen, estado.locales.size, estado.locales.size),
                style = MaterialTheme.typography.bodySmall,
                color = GrisTexto
            )
        }

        items(estado.locales, key = { it.id }) { local ->
            LocalCard(
                local = local,
                photoUrl = estado.photoUrls[local.id],
                esMiLocalClick = onEsMiLocal
            )
        }

        // Al final de la lista: cargando la página siguiente, el error o el botón para pedirla
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    estado.loadingMore -> CircularProgressIndicator()
                    estado.hayMasPaginas -> {
                        if (estado.loadMoreFailed) {
                            Text(
                                stringResource(R.string.empezamos_ver_mas_error),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        OutlinedButton(onClick = onVerMas) {
                            Text(
                                stringResource(
                                    if (estado.loadMoreFailed) R.string.empezamos_reintentar
                                    else R.string.empezamos_ver_mas
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
