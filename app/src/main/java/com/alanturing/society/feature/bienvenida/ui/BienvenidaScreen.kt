package com.alanturing.society.feature.bienvenida.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.society.R
import com.alanturing.society.ui.component.TarjetaCristal
import com.alanturing.society.ui.theme.AmarilloInterfaz
import com.alanturing.society.ui.theme.AzulInterfaz
import com.alanturing.society.ui.theme.Cristal
import com.alanturing.society.ui.theme.DynaPuff
import com.alanturing.society.ui.theme.Nunito
import com.alanturing.society.ui.theme.TextoSecundario
import com.alanturing.society.ui.theme.TintaInterfaz

@Composable
fun BienvenidaScreen(
    onDescubrir: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Imagen de fondo
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
        ) {
            // Titulo
            Text(
                //
                text = buildAnnotatedString {
                    append("Ideas que llenan ")
                    withStyle(SpanStyle(color = AzulInterfaz)) { append("tu local") }
                },
                modifier = Modifier.padding(start = 27.dp, end = 110.dp, top = 26.dp, bottom = 20.dp),
                style = TextStyle(
                    fontFamily = DynaPuff,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    lineHeight = 50.sp,
                    letterSpacing = (-0.42).sp,
                    color = TintaInterfaz
                )
            )

            // TarjetaCristal
            TarjetaCristal(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Creamos contenido con IA, estrategias de marketing y campañas que atraen clientes y hacen crecer tu negocio.",
                    style = TextStyle(
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        lineHeight = 22.5.sp,
                        color = TextoSecundario
                    )
                )

                ListaVentajas()

                // La mascota, ocupa el hueco que sobra
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.mascota_selfie),
                        contentDescription = null,
                        modifier = Modifier.size(width = 150.dp, height = 196.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                BotonDescubre(onClick = onDescubrir)
            }
        }
    }
}

// Lista con las 3 ventajas, sobre cristal claro
@Composable
private fun ListaVentajas() {
    val forma = RoundedCornerShape(20.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(forma)
            .background(Brush.verticalGradient(listOf(Cristal.Claro.arriba, Cristal.Claro.abajo)))
            .border(1.dp, Color.White.copy(alpha = 0.9f), forma)
            .padding(horizontal = 14.dp, vertical = 4.dp)
    ) {
        FilaVentaja(icono = R.drawable.ic_destello, texto = "Contenido con IA")
        HorizontalDivider(color = TintaInterfaz.copy(alpha = 0.07f))
        FilaVentaja(icono = R.drawable.ic_grafica, texto = "Estrategias de marketing")
        HorizontalDivider(color = TintaInterfaz.copy(alpha = 0.07f))
        FilaVentaja(icono = R.drawable.ic_pin, texto = "Campañas que atraen clientes")
    }
}

@Composable
private fun FilaVentaja(icono: Int, texto: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(AzulInterfaz, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(icono),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = texto,
            style = TextStyle(
                fontFamily = Nunito,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.5.sp,
                color = TintaInterfaz
            )
        )
    }
}

// Slider de cristal // Funciona como boton, preguntar si hacerlo de otra forma
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
            modifier = Modifier
                .size(50.dp)
                .background(AmarilloInterfaz, CircleShape),
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
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            style = TextStyle(
                fontFamily = Nunito,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = TintaInterfaz
            )
        )
        Image(
            painter = painterResource(R.drawable.ic_dobles_flecha),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 14.dp)
                .size(20.dp)
        )
    }
}