package com.alanturing.society.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.alanturing.society.R

// Fuentes de marca. Los .ttf van en res/font
val DynaPuff = FontFamily(
    Font(resId = R.font.dynapuff_bold, weight = FontWeight.Bold) // Solo para títulos y cifras
)

val Nunito = FontFamily(
    Font(resId = R.font.nunito_medium, weight = FontWeight.Medium),
    Font(resId = R.font.nunito_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.nunito_bold, weight = FontWeight.Bold),
    Font(resId = R.font.nunito_extrabold, weight = FontWeight.ExtraBold)
)

val Typography = Typography(
    // Título/Grande 31: «Busca tu local»
    displayMedium = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 31.sp, lineHeight = 38.sp),
    // Título/Pantalla 24: «Crear contenido»
    displaySmall = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 24.sp, lineHeight = 30.sp),
    // Título/Cifra 34: cifra grande, como «1.284»
    headlineLarge = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 34.sp, lineHeight = 40.sp),
    // Título/Cifra 22: cifra pequeña, dentro de tarjetas, como «+128 visitas»
    headlineSmall = TextStyle(fontFamily = DynaPuff, fontWeight = FontWeight.Bold, fontSize = 22.sp, lineHeight = 28.sp),
    // Cuerpo/Destacado 15: nombre de un local o de un paso
    titleMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp, lineHeight = 20.sp),
    // Cuerpo/Destacado 13: «Propuesta del mes»
    titleSmall = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp, lineHeight = 18.sp),
    // Cuerpo/Intro 15: texto introductorio de la pantalla
    bodyLarge = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Medium, fontSize = 15.sp, lineHeight = 22.sp),
    // Cuerpo/Secundario 12.5: direcciones y textos de apoyo
    bodyMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.SemiBold, fontSize = 12.5.sp, lineHeight = 18.sp),
    // Cuerpo/Pequeño 11.5: notas y fuentes, como «Fuente: Instagram»
    bodySmall = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.SemiBold, fontSize = 11.5.sp, lineHeight = 16.sp),
    // Botón/16: texto de los botones
    labelLarge = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, lineHeight = 20.sp),
    // Chip/12.5: chips y estado de cada paso («Hecho», «Ahora», «Pendiente»)
    labelMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold, fontSize = 12.5.sp, lineHeight = 18.sp),
    // Etiqueta/Sobretítulo 11.5: encima de un título.
    // TextStyle no pone mayúsculas: el texto se pasa con .uppercase() al usarlo
    labelSmall = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.ExtraBold, fontSize = 11.5.sp, lineHeight = 16.sp, letterSpacing = 0.06.em)
)