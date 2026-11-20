package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class Interlineado(val factor: Float, val etiqueta: String) {
    COMPACTO(1.0f, "Compacto"),
    NORMAL(1.3f, "Normal"),
    AMPLIO(1.6f, "Amplio")
}

class LecturaViewModel : ViewModel() {
    var tamano by mutableStateOf(18f)
    var interlineado by mutableStateOf(Interlineado.NORMAL)
    var modoOscuro by mutableStateOf(false)
    var usaSerif by mutableStateOf(false)

    fun restablecer() {
        tamano = 18f
        interlineado = Interlineado.NORMAL
        modoOscuro = false
        usaSerif = false
    }
}