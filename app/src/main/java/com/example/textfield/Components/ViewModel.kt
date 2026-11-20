package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

class CuentaViewModel : ViewModel() {
    var montoTexto by mutableStateOf("")
    var propinaPct by mutableStateOf(10)
    var usaOtro by mutableStateOf(false)
    var personas by mutableStateOf(1)
    var redondear by mutableStateOf(false)

    val monto: Double
        get() = montoTexto.toDoubleOrNull() ?: 0.0

    val propina: Double
        get() = monto * (propinaPct / 100.0)

    val total: Double
        get() = monto + propina

    val porPersona: Double
        get() {
            val base = if (personas > 0) total / personas else 0.0
            return if (redondear) ceil(base) else base
        }

    val sobrante: Double
        get() = if (redondear) (porPersona * personas) - total else 0.0
}