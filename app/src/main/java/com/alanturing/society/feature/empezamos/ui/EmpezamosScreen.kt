package com.alanturing.society.feature.empezamos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alanturing.society.R
import com.alanturing.society.feature.empezamos.model.Local
import com.alanturing.society.feature.empezamos.ui.component.BuscadorLocales
import com.alanturing.society.feature.empezamos.ui.component.LocalCard

/**
 * Pantalla 02 · Empezamos («Busca tu local»): buscador arriba y, debajo, la lista de
 * locales encontrados, una LocalCard por local. Analizar lleva a 03 · Análisis y el
 * enlace «Añádelo a mano» a S02M · Alta manual.
 */
@Composable
fun EmpezamosScreen(
    locales: List<Local>,
    onBuscar: (String) -> Unit,
    onAnalizar: (String) -> Unit,
    onAltaManual: () -> Unit,
    modifier: Modifier = Modifier
) {
    var texto by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        BuscadorLocales(
            texto = texto,
            cambioTexto = { texto = it },
            // TODO: implementar la búsqueda en EmpezamosViewModel. Cuando devuelva
            // los locales, llegan en `locales` y la LazyColumn de abajo los muestra.
            buscar = onBuscar,
            modifier = Modifier.padding(16.dp)
        )

        TextButton(
            onClick = onAltaManual,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(stringResource(R.string.empezamos_alta_manual))
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(locales, key = { it.id }) { local ->
                LocalCard(local = local, analizarClick = onAnalizar)
            }
        }
    }
}
