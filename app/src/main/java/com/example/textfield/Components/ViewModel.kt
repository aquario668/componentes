package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class LoginEstado { INACTIVO, ERROR, BLOQUEADO, EXITO }

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var recordarme by mutableStateOf(false)
    var intentosFallidos by mutableStateOf(0)
    var estado by mutableStateOf(LoginEstado.INACTIVO)

    fun onEmailChange(nuevo: String) {
        email = nuevo
        if (estado == LoginEstado.ERROR) estado = LoginEstado.INACTIVO
    }

    fun onPasswordChange(nuevo: String) {
        password = nuevo
        if (estado == LoginEstado.ERROR) estado = LoginEstado.INACTIVO
    }

    fun login() {
        if (estado == LoginEstado.BLOQUEADO) return
        if (email == "paciente@vida.mx" && password == "Vida2025") {
            estado = LoginEstado.EXITO
        } else {
            intentosFallidos++
            estado = if (intentosFallidos >= 3) LoginEstado.BLOQUEADO else LoginEstado.ERROR
        }
    }

    fun logout() {
        email = ""
        password = ""
        recordarme = false
        intentosFallidos = 0
        estado = LoginEstado.INACTIVO
    }
}