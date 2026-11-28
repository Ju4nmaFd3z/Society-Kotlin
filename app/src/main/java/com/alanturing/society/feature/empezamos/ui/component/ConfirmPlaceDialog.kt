package com.alanturing.society.feature.empezamos.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.alanturing.society.R
import com.alanturing.society.feature.empezamos.ui.ConfirmError
import com.alanturing.society.feature.empezamos.ui.ConfirmPlaceState

/**
 * Modal M02 «¿Es tu local?». La casilla «Soy propietario o gestor» es obligatoria:
 * sin marcarla no se puede confirmar (la API rechaza declaraGestor = false).
 */
@Composable
fun ConfirmPlaceDialog(
    estado: ConfirmPlaceState,
    onConfirmar: () -> Unit,
    onCerrar: () -> Unit
) {
    // Guardada en la pantalla: al girar el móvil no se desmarca
    var esGestor by rememberSaveable(estado.local.id) { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCerrar,
        title = { Text(stringResource(R.string.m02_titulo)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(estado.local.name, style = MaterialTheme.typography.titleMedium)
                Text(estado.local.address, style = MaterialTheme.typography.bodySmall)

                // Toda la fila se puede tocar, no solo el cuadradito
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.toggleable(
                        value = esGestor,
                        enabled = !estado.saving,
                        role = Role.Checkbox,
                        onValueChange = { esGestor = it }
                    )
                ) {
                    Checkbox(checked = esGestor, onCheckedChange = null)
                    Text(stringResource(R.string.m02_gestor))
                }

                estado.error?.let { error ->
                    Text(
                        text = stringResource(
                            when (error) {
                                ConfirmError.DUPLICATE -> R.string.m02_error_duplicado
                                ConfirmError.CONNECTION -> R.string.m02_error_conexion
                            }
                        ),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onConfirmar, enabled = esGestor && !estado.saving) {
                if (estado.saving) {
                    CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                } else {
                    Text(stringResource(R.string.m02_confirmar))
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onCerrar, enabled = !estado.saving) {
                Text(stringResource(R.string.m02_cancelar))
            }
        }
    )
}
