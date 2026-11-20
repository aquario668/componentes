package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.LoginEstado
import com.example.textfield.Components.LoginHoist
import com.example.textfield.Components.LoginViewModel

@Composable
fun LoginScreen(vm: LoginViewModel = viewModel()) {
    val mensajeError = if (vm.estado == LoginEstado.ERROR) {
        "Correo o contraseña incorrectos (intento ${vm.intentosFallidos} de 3)"
    } else {
        null
    }

    LoginHoist(
        estado = vm.estado,
        email = vm.email,
        onEmailChange = { vm.onEmailChange(it) },
        password = vm.password,
        onPasswordChange = { vm.onPasswordChange(it) },
        recordarme = vm.recordarme,
        onRecordarmeChange = { vm.recordarme = it },
        mensajeError = mensajeError,
        bloqueado = vm.estado == LoginEstado.BLOQUEADO,
        onLogin = { vm.login() },
        onLogout = { vm.logout() }
    )
}