package com.alanturing.society.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.alanturing.society.R

/**
 * Botón circular de cristal con la flecha de volver .
 *
 * @param onClick  qué hacer al pulsarlo
 * @param modifier para colocarlo (padding, posición...) desde la pantalla que lo usa
 */
@Composable
fun BotonVolver(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(Brush.verticalGradient(listOf(Color.White.copy(0.60f), Color.White.copy(0.42f))))
            .border(1.25.dp, Color.White.copy(alpha = 0.95f), CircleShape)
            .clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_flecha),
            contentDescription = "Volver", // Para los lectores de pantalla
            modifier = Modifier
                .size(20.dp)
                .rotate(180f)
        )
    }
}