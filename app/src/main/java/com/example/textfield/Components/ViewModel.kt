package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ResenaViewModel : ViewModel() {
    var comida by mutableStateOf(0)
    var servicio by mutableStateOf(0)
    var ambiente by mutableStateOf(0)
    var comentario by mutableStateOf("")
    var etiquetas by mutableStateOf(setOf<String>())
    var publicada by mutableStateOf(false)

    val todasCalificadas: Boolean
        get() = comida > 0 && servicio > 0 && ambiente > 0

    val general: Double
        get() = if (todasCalificadas) (comida + servicio + ambiente) / 3.0 else 0.0

    val puedePublicar: Boolean
        get() = todasCalificadas && comentario.length >= 20

    fun toggleComida(valor: Int) {
        comida = if (comida == valor) 0 else valor
    }

    fun toggleServicio(valor: Int) {
        servicio = if (servicio == valor) 0 else valor
    }

    fun toggleAmbiente(valor: Int) {
        ambiente = if (ambiente == valor) 0 else valor
    }

    fun toggleEtiqueta(etiqueta: String) {
        etiquetas = if (etiqueta in etiquetas) etiquetas - etiqueta else etiquetas + etiqueta
    }

    fun actualizarComentario(texto: String) {
        if (texto.length <= 200) comentario = texto
    }
}