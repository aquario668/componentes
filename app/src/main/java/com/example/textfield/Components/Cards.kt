package com.example.textfield.Components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginHoist(
    estado: LoginEstado,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    recordarme: Boolean,
    onRecordarmeChange: (Boolean) -> Unit,
    mensajeError: String?,
    bloqueado: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    if (estado == LoginEstado.EXITO) {
        HomeLogin(email = email, onLogout = onLogout)
    } else {
        FormularioLogin(
            email = email,
            onEmailChange = onEmailChange,
            password = password,
            onPasswordChange = onPasswordChange,
            recordarme = recordarme,
            onRecordarmeChange = onRecordarmeChange,
            mensajeError = mensajeError,
            bloqueado = bloqueado,
            onLogin = onLogin
        )
    }
}

@Composable
fun FormularioLogin(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    recordarme: Boolean,
    onRecordarmeChange: (Boolean) -> Unit,
    mensajeError: String?,
    bloqueado: Boolean,
    onLogin: () -> Unit
) {
    var mostrarPassword by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Clínica Vida")
        TextField(value = email, onValueChange = onEmailChange, label = { Text("Correo") })
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { mostrarPassword = !mostrarPassword }) { Text(if (mostrarPassword) "Ocultar" else "Ver") }
            }
        )
        Row {
            Checkbox(checked = recordarme, onCheckedChange = onRecordarmeChange)
            Text("Recordarme")
        }
        if (mensajeError != null) Text(mensajeError)
        if (bloqueado) Text("Bloqueado. Intente más tarde.")
        Button(onClick = onLogin, enabled = !bloqueado) { Text("Entrar") }
    }
}

@Composable
fun HomeLogin(email: String, onLogout: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Bienvenido, $email")
        Button(onClick = onLogout) { Text("Cerrar sesión") }
    }
}