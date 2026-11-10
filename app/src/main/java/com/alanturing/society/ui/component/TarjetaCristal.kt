package com.alanturing.society.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alanturing.society.ui.theme.CristalAbajo
import com.alanturing.society.ui.theme.CristalArriba

// Superficie de cristal del Figma.

@Composable
fun TarjetaCristal(
    modifier: Modifier = Modifier,
    contenido: @Composable ColumnScope.() -> Unit
) {
    val forma = RoundedCornerShape(28.dp)
    Column(
        modifier = modifier
            .shadow(12.dp, forma, clip = false)
            .clip(forma)
            .background(Brush.verticalGradient(listOf(CristalArriba, CristalAbajo)))
            .border(1.25.dp, Color.White.copy(alpha = 0.9f), forma)
            .padding(horizontal = 24.dp, vertical = 22.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = contenido
    )
}