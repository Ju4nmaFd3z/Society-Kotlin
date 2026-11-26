package com.alanturing.society.feature.altamanual.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanturing.society.R
import com.alanturing.society.feature.arranque.model.PlaceType
import com.alanturing.society.ui.component.BotonVolver

/**
 * Pantalla 02M · Alta manual («Añade tu local»): formulario para dar de alta el local
 * cuando no sale en la búsqueda. Cada campo muestra su error debajo y «Guardar y
 * analizar» da de alta el local y lleva a 03 · Análisis.
 */
@Composable
fun AltaManualScreen(
    estado: AltaManualUiState,
    onNameChange: (String) -> Unit,
    onTypeChange: (PlaceType) -> Unit,
    onAddressChange: (String) -> Unit,
    onTownChange: (String) -> Unit,
    onWebChange: (String) -> Unit,
    onInstagramChange: (String) -> Unit,
    onManagerChange: (Boolean) -> Unit,
    onGuardar: () -> Unit,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    val form = estado.form
    val errores = estado.errors
    val activo = !estado.saving // mientras se guarda no se puede tocar nada

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding() // que el teclado no tape el campo en el que se escribe
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BotonVolver(onClick = onVolver)
            Text(stringResource(R.string.altamanual_titulo), style = MaterialTheme.typography.titleLarge)
        }

        CampoTexto(
            valor = form.name,
            onCambio = onNameChange,
            etiqueta = R.string.altamanual_nombre,
            error = errores.name,
            activo = activo,
            mayusculas = KeyboardCapitalization.Words
        )

        Text(stringResource(R.string.altamanual_tipo), style = MaterialTheme.typography.bodyMedium)
        SelectorTipo(seleccionado = form.type, onCambio = onTypeChange, activo = activo)
        errores.type?.let { TextoError(stringResource(R.string.altamanual_error_tipo)) }

        CampoTexto(
            valor = form.address,
            onCambio = onAddressChange,
            etiqueta = R.string.altamanual_direccion,
            error = errores.address,
            activo = activo,
            mayusculas = KeyboardCapitalization.Sentences
        )

        CampoTexto(
            valor = form.town,
            onCambio = onTownChange,
            etiqueta = R.string.altamanual_municipio,
            error = errores.town,
            activo = activo,
            mayusculas = KeyboardCapitalization.Words
        )

        CampoTexto(
            valor = form.web,
            onCambio = onWebChange,
            etiqueta = R.string.altamanual_web,
            error = errores.web,
            activo = activo,
            teclado = KeyboardType.Uri,
            placeholder = "https://"
        )

        CampoTexto(
            valor = form.instagram,
            onCambio = onInstagramChange,
            etiqueta = R.string.altamanual_instagram,
            error = errores.instagram,
            activo = activo,
            ultimo = true
        )

        // Toda la fila se puede tocar, no solo el cuadradito
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.toggleable(
                value = form.isManager,
                enabled = activo,
                role = Role.Checkbox,
                onValueChange = onManagerChange
            )
        ) {
            Checkbox(checked = form.isManager, onCheckedChange = null)
            Text(stringResource(R.string.altamanual_gestor))
        }
        errores.isManager?.let { TextoError(stringResource(R.string.altamanual_error_gestor)) }

        estado.saveError?.let { error ->
            TextoError(
                stringResource(
                    when (error) {
                        SaveError.DUPLICATE -> R.string.altamanual_error_duplicado
                        SaveError.INVALID -> R.string.altamanual_error_invalido
                        SaveError.SERVER -> R.string.altamanual_error_servidor
                    }
                )
            )
        }

        // Tras un error el mismo botón sirve para reintentar
        Button(
            onClick = onGuardar,
            enabled = activo,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (estado.saving) {
                CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
            } else {
                Text(stringResource(R.string.altamanual_guardar))
            }
        }
    }
}

// Campo con su etiqueta y, si lo hay, el error debajo
@Composable
private fun CampoTexto(
    valor: String,
    onCambio: (String) -> Unit,
    etiqueta: Int,
    error: FieldError?,
    activo: Boolean,
    mayusculas: KeyboardCapitalization = KeyboardCapitalization.None,
    teclado: KeyboardType = KeyboardType.Text,
    placeholder: String? = null,
    ultimo: Boolean = false // el último cierra el teclado en vez de saltar al siguiente
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(stringResource(etiqueta)) },
        placeholder = placeholder?.let { { Text(it) } },
        isError = error != null,
        supportingText = error?.let { { Text(textoDeError(it)) } },
        enabled = activo,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = mayusculas,
            keyboardType = teclado,
            imeAction = if (ultimo) ImeAction.Done else ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun textoDeError(error: FieldError): String = when (error) {
    FieldError.Required -> stringResource(R.string.altamanual_error_obligatorio)
    is FieldError.TooShort -> pluralStringResource(R.plurals.altamanual_error_corto, error.min, error.min)
    FieldError.InvalidWeb -> stringResource(R.string.altamanual_error_web)
    FieldError.InvalidInstagram -> stringResource(R.string.altamanual_error_instagram)
}

// Un chip por tipo de local; solo puede haber uno marcado
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SelectorTipo(seleccionado: PlaceType?, onCambio: (PlaceType) -> Unit, activo: Boolean) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        PlaceType.entries.forEach { tipo ->
            FilterChip(
                selected = tipo == seleccionado,
                onClick = { onCambio(tipo) },
                label = { Text(stringResource(nombreDeTipo(tipo))) },
                enabled = activo
            )
        }
    }
}

private fun nombreDeTipo(tipo: PlaceType): Int = when (tipo) {
    PlaceType.RESTAURANTE -> R.string.altamanual_tipo_restaurante
    PlaceType.BAR -> R.string.altamanual_tipo_bar
    PlaceType.CAFETERIA -> R.string.altamanual_tipo_cafeteria
    PlaceType.OTRO -> R.string.altamanual_tipo_otro
}

@Composable
private fun TextoError(texto: String) {
    Text(texto, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
}

@Preview(showBackground = true, name = "Con errores")
@Composable
private fun PreviewConErrores() {
    AltaManualScreen(
        estado = AltaManualUiState(
            form = ManualForm(name = "B", web = "boqueron.es"),
            errors = ManualForm(name = "B", web = "boqueron.es").validate()
        ),
        onNameChange = {}, onTypeChange = {}, onAddressChange = {}, onTownChange = {},
        onWebChange = {}, onInstagramChange = {}, onManagerChange = {}, onGuardar = {}, onVolver = {}
    )
}
