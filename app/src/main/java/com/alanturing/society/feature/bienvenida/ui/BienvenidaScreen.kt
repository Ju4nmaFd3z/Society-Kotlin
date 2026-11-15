package com.alanturing.society.feature.bienvenida.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.society.R
import com.alanturing.society.ui.component.TarjetaCristal
import com.alanturing.society.ui.theme.AmarilloInterfaz
import com.alanturing.society.ui.theme.Cristal
import com.alanturing.society.ui.theme.Nunito
import com.alanturing.society.ui.theme.TintaInterfaz

// S01 · Bienvenida (versión de prueba): fondo, título, mascota y botón
@Composable
fun BienvenidaScreen(
    onDescubrir: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.fondo_grafiti),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            TarjetaCristal(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Text(
                    text = "Ideas que llenan tu local",
                    fontFamily = Nunito,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = TintaInterfaz
                )
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.mascota_megafono),
                        contentDescription = null,
                        modifier = Modifier.size(width = 150.dp, height = 196.dp)
                    )
                }
                BotonDescubre(onClick = onDescubrir)
            }
        }
    }
}

// Botón con el cristal de Cristal.kt
@Composable
private fun BotonDescubre(onClick: () -> Unit) {
    val forma = RoundedCornerShape(30.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(forma)
            .background(Brush.verticalGradient(listOf(Cristal.Estandar.arriba, Cristal.Estandar.abajo)))
            .border(1.25.dp, Color.White.copy(alpha = 0.95f), forma)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(50.dp).background(AmarilloInterfaz, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_dobles_boton),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
        }
        Text(
            text = "Descubre cómo funciona",
            modifier = Modifier.weight(1f).padding(start = 16.dp),
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = TintaInterfaz
        )
    }
}
