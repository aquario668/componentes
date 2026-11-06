package com.example.textfield.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun LoginForm(
    email: String, onEmailChange: (String) -> Unit,
    password: String, onPasswordChange: (String) -> Unit,
    recordarme: Boolean, onRecordarmeChange: (Boolean) -> Unit,
    mensajeError: String?, bloqueado: Boolean, onLogin: () -> Unit
) {
    Column {
        TextField(value = email, onValueChange = onEmailChange, label = { Text("Correo") })
        var mostrarPassword by remember { mutableStateOf(false) }
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { mostrarPassword = !mostrarPassword }) { Text("👁") }
            }
        )

        Row {
            Checkbox(checked = recordarme, onCheckedChange = onRecordarmeChange)
            Text("Recordarme")
        }

        if (mensajeError != null) Text(mensajeError, color = MaterialTheme.colorScheme.error)
        if (bloqueado) Text("Acceso bloqueado. Intente más tarde.", color = MaterialTheme.colorScheme.error)

        Button(onClick = onLogin, enabled = !bloqueado) { Text("Entrar") }
    }
}

@Composable
fun HomeScreen(email: String, onLogout: () -> Unit) {
    Column {
        Text("Bienvenido, $email")
        Button(onClick = onLogout) { Text("Cerrar sesión") }
    }
}