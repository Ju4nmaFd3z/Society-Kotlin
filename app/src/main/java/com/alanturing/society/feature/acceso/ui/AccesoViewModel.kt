package com.alanturing.society.feature.acceso.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import com.alanturing.society.feature.acceso.model.ResultadoAcceso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class AccesoViewModel @Inject constructor(
    private val repository: AuthRepository // Dependencia a repo
) : ViewModel() {

    //Estado de la pantalla
    private val _estado=MutableStateFlow<AccesoUiState>(AccesoUiState.Formulario)

    val estado: StateFlow<AccesoUiState> = _estado.asStateFlow()

    fun continuar(creando: Boolean, email: String, password: String) {
        //si hay una peticion en curso, se ignora la pulsacion
        if (_estado.value== AccesoUiState.Enviando) return
        _estado.value= AccesoUiState.Enviando

        //Corrutina
        viewModelScope.launch {
            val resultado= if (creando) repository.registro(email, password)
            else repository.acceso(email, password)

            // Correcto: la pantalla navega. Fallo: muestra el error
            _estado.value=when(resultado){
                ResultadoAcceso.Correcto -> AccesoUiState.Hecho
                is ResultadoAcceso.Fallo -> AccesoUiState.Error(resultado.error)
            }
        }
    }
    //Evita que se dispare la navegacion una segunda vez
    fun navegacionHecha(){
        _estado.value= AccesoUiState.Formulario

    }
}