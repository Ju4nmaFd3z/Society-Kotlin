package com.alanturing.society.ui.theme

import androidx.compose.ui.graphics.Color

data class EstiloCristal(
    val arriba: Color,
    val abajo: Color,
    val sombraCerca: Float,
    val sombraMedia: Float,
    val sombraLejos: Float
)

object Cristal {
    val Claro = EstiloCristal(Color.White.copy(0.46f), Color.White.copy(0.28f), 0.08f, 0.16f, 0.10f)
    val Estandar = EstiloCristal(Color.White.copy(0.60f), Color.White.copy(0.42f), 0.10f, 0.20f, 0.13f)
    val Denso = EstiloCristal(Color.White.copy(0.78f), Color.White.copy(0.66f), 0.10f, 0.20f, 0.13f)
}