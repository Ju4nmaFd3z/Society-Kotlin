package com.alanturing.society.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Punto de arranque de Hilt. Sin esta clase, Hilt no puede crear el ViewModel de ninguna pantalla.
@HiltAndroidApp
class SocietyApplication : Application()
