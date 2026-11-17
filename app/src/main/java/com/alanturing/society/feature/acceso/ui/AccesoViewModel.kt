package com.alanturing.society.feature.acceso.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AccesoViewModel @Inject constructor(
    private val repository: AuthRepository // Dependencia a repo (el TODO del profesor)
    ) : ViewModel() {

    fun continuar(creando: Boolean, email: String, password: String) {
        viewModelScope.launch {
            if (creando) repository.registro(email, password)
            else repository.acceso(email, password)
            }
        }
    }