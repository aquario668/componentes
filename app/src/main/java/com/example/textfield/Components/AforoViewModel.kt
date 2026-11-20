package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AforoViewModel : ViewModel() {
    val aforoMax = 50
    var actuales by mutableStateOf(0)
    var visitasDelDia by mutableStateOf(0)

    val porcentaje: Float
        get() = actuales / aforoMax.toFloat()

    val porcentajeTexto: Int
        get() = (porcentaje * 100).toInt()

    val estadoTexto: String
        get() = when {
            actuales >= aforoMax -> "Aforo lleno"
            porcentaje >= 0.7f -> "Cerca del límite"
            else -> "Aforo disponible"
        }

    fun entra() {
        if (actuales < aforoMax) {
            actuales++
            visitasDelDia++
        }
    }

    fun sale() {
        if (actuales > 0) {
            actuales--
        }
    }

    fun cerrarDia() {
        actuales = 0
        visitasDelDia = 0
    }
}