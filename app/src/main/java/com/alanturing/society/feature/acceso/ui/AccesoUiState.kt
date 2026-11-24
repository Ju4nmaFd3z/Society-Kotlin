package com.alanturing.society.feature.acceso.ui

//Estados de la pantalla de acceso
sealed interface AccesoUiState{
    data object Formulario: AccesoUiState       //Esperando datos
    data object Enviando: AccesoUiState         //Peticion en curso
    data class  Error(val error: Throwable): AccesoUiState   //Lleva la excepción del fallo
    data class Hecho(val destino: Any): AccesoUiState   //Peticion completada sin error. Lleva la ruta a la que ir
}

