package com.alanturing.society.feature.acceso.ui

import com.alanturing.society.feature.acceso.model.ErrorAcceso

//Estados de la pantalla de acceso
sealed interface AccesoUiState{
    data object Formulario: AccesoUiState       //Esperando datos
    data object Enviando: AccesoUiState         //Peticion en curso
    data class  Error(val error: ErrorAcceso): AccesoUiState
    data object Hecho: AccesoUiState            //Peticion completada sin error
}

