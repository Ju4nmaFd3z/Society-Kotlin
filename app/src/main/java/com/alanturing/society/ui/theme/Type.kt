package com.alanturing.society.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.alanturing.society.R

// Fuentes de marca. Los .ttf van en res/font (nombres en minúscula y con guion bajo)
val DynaPuff = FontFamily(
    Font(R.font.dynapuff_bold, FontWeight.Bold) // Solo para los títulos grandes
)

val Nunito = FontFamily(
    Font(R.font.nunito_medium, FontWeight.Medium),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold)
)

// Cada estilo tiene un uso fijo en las pantallas, así no se repiten tamaños a mano
val Typography = Typography(
    // Título de 01 · Bienvenida: «Ideas que llenan tu local»
    displayMedium = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 40.sp, lineHeight = 42.sp),
    // Título del resto de pantallas: «Busca tu local», «Estudiando tu local»
    displaySmall = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 32.sp, lineHeight = 34.sp),
    // Cifra grande, como el «3 de 5» del análisis (Título/Cifra 34)
    headlineLarge = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 34.sp),
    // Cifra pequeña, dentro de tarjetas (Título/Cifra 22)
    headlineSmall = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 22.sp),
    // Nombre de un local, de una ventaja o de un paso
    titleMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp, lineHeight = 20.sp),
    // Texto de la hoja de cristal y del buscador
    bodyLarge = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 15.sp, lineHeight = 22.sp),
    bodyMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    // Detalles grises: dirección, «695 reseñas leídas», autor de la foto
    bodySmall = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),
    // Texto de los botones
    labelLarge = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, lineHeight = 20.sp),
    // Chips y estado de cada paso: «Hecho», «Ahora», «Pendiente» (Chip/12.5)
    labelMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold, fontSize = 12.5.sp),
    // Sobretítulo encima de un título (Etiqueta/Sobretítulo 11.5).
    // TextStyle no pone mayúsculas: el texto se pasa con .uppercase() al usarlo
    labelSmall = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 11.5.sp, letterSpacing = 0.06.em)
)
