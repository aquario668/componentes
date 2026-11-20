package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InscripcionViewModel : ViewModel() {
    var nombre by mutableStateOf("")
    var correo by mutableStateOf("")
    var edad by mutableStateOf("")
    var taller by mutableStateOf("Seleccionar taller...")
    var aceptaAviso by mutableStateOf(false)
    var intentoEnviar by mutableStateOf(false)
    var inscrito by mutableStateOf(false)

    val errorNombre: Boolean
        get() = nombre.length < 3

    val errorCorreo: Boolean
        get() = !correo.contains("@") || !correo.substringAfter("@").contains(".")

    val errorEdad: Boolean
        get() {
            val num = edad.toIntOrNull()
            return num == null || num !in 12..99
        }

    val errorTaller: Boolean
        get() = taller == "Seleccionar taller..."

    val errorAviso: Boolean
        get() = !aceptaAviso

    fun enviar() {
        intentoEnviar = true
        if (!errorNombre && !errorCorreo && !errorEdad && !errorTaller && !errorAviso) {
            inscrito = true
        }
    }

    fun limpiar() {
        nombre = ""
        correo = ""
        edad = ""
        taller = "Seleccionar taller..."
        aceptaAviso = false
        intentoEnviar = false
        inscrito = false
    }
}