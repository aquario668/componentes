package com.example.textfield.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InscripcionHoist(
    inscrito: Boolean,
    nombre: String,
    onNombreChange: (String) -> Unit,
    errorNombre: Boolean,
    correo: String,
    onCorreoChange: (String) -> Unit,
    errorCorreo: Boolean,
    edad: String,
    onEdadChange: (String) -> Unit,
    errorEdad: Boolean,
    taller: String,
    onTallerChange: (String) -> Unit,
    errorTaller: Boolean,
    aceptaAviso: Boolean,
    onAvisoChange: (Boolean) -> Unit,
    errorAviso: Boolean,
    onEnviar: () -> Unit,
    onNueva: () -> Unit
) {
    if (inscrito) {
        ConfirmacionInscripcion(nombre = nombre, correo = correo, taller = taller, onNueva = onNueva)
    } else {
        FormularioInscripcion(
            nombre = nombre,
            onNombreChange = onNombreChange,
            errorNombre = errorNombre,
            correo = correo,
            onCorreoChange = onCorreoChange,
            errorCorreo = errorCorreo,
            edad = edad,
            onEdadChange = onEdadChange,
            errorEdad = errorEdad,
            taller = taller,
            onTallerChange = onTallerChange,
            errorTaller = errorTaller,
            aceptaAviso = aceptaAviso,
            onAvisoChange = onAvisoChange,
            errorAviso = errorAviso,
            onEnviar = onEnviar
        )
    }
}

@Composable
fun FormularioInscripcion(
    nombre: String,
    onNombreChange: (String) -> Unit,
    errorNombre: Boolean,
    correo: String,
    onCorreoChange: (String) -> Unit,
    errorCorreo: Boolean,
    edad: String,
    onEdadChange: (String) -> Unit,
    errorEdad: Boolean,
    taller: String,
    onTallerChange: (String) -> Unit,
    errorTaller: Boolean,
    aceptaAviso: Boolean,
    onAvisoChange: (Boolean) -> Unit,
    errorAviso: Boolean,
    onEnviar: () -> Unit
) {
    var menuExpandido by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextField(value = nombre, onValueChange = onNombreChange, label = { Text("Nombre completo") }, isError = errorNombre)
        if (errorNombre) Text("Mínimo 3 caracteres")

        TextField(value = correo, onValueChange = onCorreoChange, label = { Text("Correo electrónico") }, isError = errorCorreo)
        if (errorCorreo) Text("Correo inválido")

        TextField(value = edad, onValueChange = onEdadChange, label = { Text("Edad") }, isError = errorEdad)
        if (errorEdad) Text("Edad entre 12 y 99")

        Box {
            Button(onClick = { menuExpandido = true }) { Text(taller) }
            DropdownMenu(expanded = menuExpandido, onDismissRequest = { menuExpandido = false }) {
                listOf("Pintura", "Fotografía", "Guitarra").forEach { op ->
                    DropdownMenuItem(text = { Text(op) }, onClick = { onTallerChange(op); menuExpandido = false })
                }
            }
        }
        if (errorTaller) Text("Elige un taller")

        Row {
            Checkbox(checked = aceptaAviso, onCheckedChange = onAvisoChange)
            Text("Acepto aviso de privacidad")
        }
        if (errorAviso) Text("Debes aceptar el aviso")

        Button(onClick = onEnviar) { Text("Inscribirme") }
    }
}

@Composable
fun ConfirmacionInscripcion(nombre: String, correo: String, taller: String, onNueva: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("¡Inscripción Exitosa!")
        Text("Nombre: $nombre")
        Text("Correo: $correo")
        Text("Taller: $taller")
        Button(onClick = onNueva) { Text("Nueva inscripción") }
    }
}