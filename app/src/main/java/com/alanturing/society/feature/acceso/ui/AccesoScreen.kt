package com.alanturing.society.feature.acceso.ui

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alanturing.society.R
import com.alanturing.society.feature.acceso.data.remote.exception.CredentialsNotValidException
import com.alanturing.society.feature.acceso.data.remote.exception.DataNotValidException
import com.alanturing.society.feature.acceso.data.remote.exception.EmailAlreadyInUseException
import com.alanturing.society.feature.acceso.data.remote.exception.NoConnectionException
import com.alanturing.society.feature.acceso.data.remote.exception.TooManyAttemptsException
import com.alanturing.society.ui.component.BotonVolver
import com.alanturing.society.ui.theme.Alerta
import com.alanturing.society.ui.theme.AmarilloInterfaz
import com.alanturing.society.ui.theme.AzulInterfaz
import com.alanturing.society.ui.theme.DynaPuff
import com.alanturing.society.ui.theme.Nunito
import com.alanturing.society.ui.theme.SocietyTheme
import com.alanturing.society.ui.theme.TextoTerciario
import com.alanturing.society.ui.theme.TintaInterfaz

private val CampoBlanco = Color.White.copy(alpha = 0.84f)

// Los dos modos de la pantalla. (Registro o login)
enum class ModoAcceso { CREAR_CUENTA, INICIAR_SESION }

@Composable
fun AccesoScreen(
    estado: AccesoUiState,
    onContinuar: (creando: Boolean, email: String, password: String)-> Unit,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Compose redibuja la pantalla al cambiar el estado. conserva el valor al rotar el móvil.
    var modo by rememberSaveable { mutableStateOf(ModoAcceso.CREAR_CUENTA) }

    // Guarda true si estamos en Crear cuenta
    val creando = modo == ModoAcceso.CREAR_CUENTA

    // Casilla de términos
    var aceptaTerminos by rememberSaveable { mutableStateOf(false) }

    // Campos de login/register
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Comprueba si los campos estan rellenos y se han aceptado los terminos
    val esValido = email.isNotBlank() && password.isNotBlank() && (aceptaTerminos || !creando)

    Box(modifier = modifier.fillMaxSize()) {
        // Fondo de pared
        Image(
            painter = painterResource(R.drawable.fondo_grafiti),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            // Contenido
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, top = 96.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Le pasamos el modo actual y que hacer cuando se pulsa una pestaña
                SelectorAcceso(modo = modo, onModo = { modo = it })

                Campo(
                    etiqueta = "Correo electrónico",
                    icono = R.drawable.ic_mail,
                    valor = email,
                    onValor = { email = it },
                    placeholder = "tu-correo@ejemplo.com",
                )
                Campo(

                    etiqueta = "Contraseña",
                    icono = R.drawable.ic_lock,
                    valor = password,
                    onValor = { password = it },
                    placeholder = "••••••••••",
                    visual = PasswordVisualTransformation(), // Muestra puntos en vez de las letras
                    iconoFinal = R.drawable.ic_ojo,
                    ayuda = if (creando) "Mínimo 8 caracteres" else null // Solo al crear la cuenta
                )

                // Los términos solo se aceptan al crear la cuenta
                if (creando) Terminos(
                    marcada = aceptaTerminos,
                    onCambio = { aceptaTerminos = it }
                )

                val enviando=estado== AccesoUiState.Enviando

                BotonContinuar(
                    habilitado = esValido && !enviando,
                    onClick = {
                        onContinuar(creando,email,password)
                    }
                )

                if(estado is AccesoUiState.Error){
                    Text(
                        text = mensajeDe(estado.error),
                        style = TextStyle(
                            fontFamily = Nunito,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp,
                            color = Alerta
                        )
                    )
                }

                // Texto olvidaste tu contraseña - Solo activo en iniciar sesion.
                if (!creando) {
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(role = Role.Button) { /* TODO: pantalla o flujo de recuperar contraseña */ },
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = Nunito,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp,
                            color = TextoTerciario
                        )
                    )
                }
            }

            // Boton para volver atras
            BotonVolver(
                onClick = onVolver,
                modifier = Modifier.padding(start = 16.dp, top = 2.dp)
            )
            //Titulo
            Text(
                text = "Tu cuenta",
                modifier = Modifier.padding(start = 106.dp, top = 2.dp),
                style = TextStyle(
                    fontFamily = DynaPuff,
                    fontWeight = FontWeight.Bold,
                    fontSize = 31.sp,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.31).sp,
                    color = TintaInterfaz
                )
            )
            // Imagen mascota
            Image(
                painter = painterResource(R.drawable.mascota_selfie),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset { IntOffset(x = (-22).dp.roundToPx(), y = (-14).dp.roundToPx()) }
                    .size(width = 86.dp, height = 112.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}


// Conteneror con las dos pestañas. Recibe el modo activo y avisa con onModo cuando el usuario pulsa una pestaña
@Composable
private fun SelectorAcceso(modo: ModoAcceso, onModo: (ModoAcceso) -> Unit) {
    val forma = RoundedCornerShape(24.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(forma)
            .background(Brush.verticalGradient(listOf(Color.White.copy(0.60f), Color.White.copy(0.42f))))
            .border(1.25.dp, Color.White.copy(alpha = 0.95f), forma)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Cada pestaña se pinta como activa si su modo coincide con el actual
        Pestana(
            texto = "Crear cuenta",
            activa = modo == ModoAcceso.CREAR_CUENTA,
            onClick = { onModo(ModoAcceso.CREAR_CUENTA) },
            modifier = Modifier.weight(1f)
        )
        Pestana(
            texto = "Iniciar sesión",
            activa = modo == ModoAcceso.INICIAR_SESION,
            onClick = { onModo(ModoAcceso.INICIAR_SESION) },
            modifier = Modifier.weight(1f)
        )
    }
}

// Una pestaña: la activa lleva fondo blanco y sombra, la inactiva solo texto. onClick avisa al pulsarla
@Composable
private fun Pestana(texto: String, activa: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val forma = RoundedCornerShape(20.dp)
    Box(
        modifier = modifier
            .then(if (activa) Modifier.shadow(6.dp, forma, clip = false) else Modifier)
            .clip(forma)
            .then(if (activa) Modifier.background(CampoBlanco) else Modifier)
            .clickable(onClick = onClick)
            .padding(vertical = 11.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            style = TextStyle(
                fontFamily = Nunito,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                color = if (activa) TintaInterfaz else TextoTerciario
            )
        )
    }
}

// Campos de texto - Recibe el valor y avisa con onValor cada vez que el usuario cambia el texto
@Composable
private fun Campo(
    etiqueta: String,
    icono: Int,
    valor: String,
    onValor: (String) -> Unit,
    placeholder: String,
    visual: VisualTransformation = VisualTransformation.None, // Para ocultar la contraseña con puntos
    iconoFinal: Int? = null,
    ayuda: String? = null
) {
    // Solo lo usa el campo con icono final (la contraseña). Empieza oculta
    var oculta by rememberSaveable { mutableStateOf(true) }
    val estiloTexto = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 13.sp,
        color = TintaInterfaz
    )
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = etiqueta.uppercase(),
            style = TextStyle(
                fontFamily = Nunito,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 11.5.sp,
                letterSpacing = 0.69.sp,
                color = TextoTerciario
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(CampoBlanco)
                .padding(horizontal = 14.dp, vertical = 13.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(icono), contentDescription = null, modifier = Modifier.size(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                // El placeholder
                if (valor.isEmpty()) {
                    Text(text = placeholder, style = estiloTexto.copy(color = TextoTerciario.copy(alpha = 0.55f)))
                }

                BasicTextField(
                    value = valor,
                    onValueChange = onValor,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true, // No hay saltos
                    visualTransformation = if (iconoFinal != null && !oculta) VisualTransformation.None else visual,
                    textStyle = estiloTexto,
                    cursorBrush = SolidColor(TintaInterfaz)
                )
            }
            if (iconoFinal != null) {
                Image(
                    painter = painterResource(iconoFinal),
                    contentDescription = "Mostrar u ocultar contraseña",
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { oculta = !oculta }
                )
            }
        }
        if (ayuda != null) {
            Text(
                text = ayuda,
                style = TextStyle(
                    fontFamily = Nunito,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.5.sp,
                    color = TextoTerciario
                )
            )
        }
    }
}

// Casilla de términos y condicions - recibe si está marcada y avisa con onCambio
@Composable
private fun Terminos(marcada: Boolean, onCambio: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(role = Role.Checkbox) { onCambio(!marcada) }, // Invierte el valor al pulsar
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(if (marcada) AzulInterfaz else CampoBlanco)
                .border(1.5.dp, if (marcada) AzulInterfaz else TextoTerciario, RoundedCornerShape(7.dp)),
            contentAlignment = Alignment.Center
        ) {
            //check si esta marcada
            if (marcada) {
                Image(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
        Text(
            text = "Acepto los términos y la política de privacidad.",
            modifier = Modifier.weight(1f),
            style = TextStyle(
                fontFamily = Nunito,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.5.sp,
                color = TextoTerciario
            )
        )
    }
}

//Boton amarillo
@Composable
private fun BotonContinuar(habilitado: Boolean, onClick: () -> Unit) {
    val forma = RoundedCornerShape(27.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)

            //Sombra amarilla solo aparece si el botón esta activo
            .then(
                if (habilitado) Modifier.shadow(12.dp, forma, ambientColor = Color(0xFFBF8500), spotColor = Color(0xFFBF8500))
                else Modifier
            )

            .clip(forma)
            .background(if (habilitado) AmarilloInterfaz else AmarilloInterfaz.copy(alpha = 0.45f))
            .clickable(enabled = habilitado, role = Role.Button, onClick = onClick), // enabled = false: ignora los toques
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Continuar",
            style = TextStyle(
                fontFamily = Nunito,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = if (habilitado) TintaInterfaz else TintaInterfaz.copy(alpha = 0.45f)
            )
        )
    }
}

// Mensaje para cada excepción. ServerErrorException y cualquier otra caen en el else
private fun mensajeDe(error: Throwable): String = when(error){
    is DataNotValidException -> "Revisa el correo y la contraseña (min 8 caracteres)"
    is CredentialsNotValidException -> "Sus credenciales son incorrectas"
    is EmailAlreadyInUseException -> "Ya existe una cuenta asociada a este correo"
    is TooManyAttemptsException -> "Demasiados intentos, vuelva a probar mas tarde"
    is NoConnectionException -> "No se pudo conectar con el servidor"
    else -> "Error del servidor"
}


@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun AccesoScreenPreview() {
    SocietyTheme {
        AccesoScreen(
            AccesoUiState.Formulario,
            onContinuar = {_, _, _ ->},
            onVolver = {}
        )
    }
}