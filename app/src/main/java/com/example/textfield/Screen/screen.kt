package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfield.Components.HomeScreen
import com.example.textfield.Components.LoginForm


enum class LoginEstado { INACTIVO, ERROR, BLOQUEADO, EXITO }

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var recordarme by remember { mutableStateOf(false) }
    var intentosFallidos by remember { mutableStateOf(0) }
    var estado by remember { mutableStateOf(LoginEstado.INACTIVO) }
    Column(modifier = Modifier.fillMaxSize().background(Color.White), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("CLINICA VIDA", fontSize = 20.sp, color = Color.Green)
        Spacer(modifier=Modifier.height(8.dp))
        Text("Consulta tus citas medicas", color = Color.Green)
        Spacer(modifier=Modifier.height(16.dp))

        when (estado) {
            LoginEstado.EXITO -> {
                HomeScreen(
                    email = email,
                    onLogout = {
                        email = ""; password = ""; recordarme = false
                        intentosFallidos = 0; estado = LoginEstado.INACTIVO
                    }
                )
            }

            else -> {
                LoginForm(
                    email = email,
                    onEmailChange = { email = it; estado = LoginEstado.INACTIVO },
                    password = password,
                    onPasswordChange = { password = it; estado = LoginEstado.INACTIVO },
                    recordarme = recordarme,
                    onRecordarmeChange = { recordarme = it },
                    mensajeError = if (estado == LoginEstado.ERROR) "Correo o contraseña incorrectos (intento $intentosFallidos de 3)" else null,
                    bloqueado = estado == LoginEstado.BLOQUEADO,
                    onLogin = {
                        if (email == "paciente@vida.mx" && password == "Vida2025") {
                            estado = LoginEstado.EXITO
                        } else {
                            intentosFallidos++
                            estado =
                                if (intentosFallidos >= 3) LoginEstado.BLOQUEADO else LoginEstado.ERROR
                        }
                    }
                )
            }
        }
    }
}