package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.InscripcionHoist
import com.example.textfield.Components.InscripcionViewModel

@Composable
fun InscripcionScreen(vm: InscripcionViewModel = viewModel()) {
    InscripcionHoist(
        inscrito = vm.inscrito,
        nombre = vm.nombre,
        onNombreChange = { vm.nombre = it },
        errorNombre = vm.intentoEnviar && vm.errorNombre,
        correo = vm.correo,
        onCorreoChange = { vm.correo = it },
        errorCorreo = vm.intentoEnviar && vm.errorCorreo,
        edad = vm.edad,
        onEdadChange = { if (it.all { c -> c.isDigit() }) vm.edad = it },
        errorEdad = vm.intentoEnviar && vm.errorEdad,
        taller = vm.taller,
        onTallerChange = { vm.taller = it },
        errorTaller = vm.intentoEnviar && vm.errorTaller,
        aceptaAviso = vm.aceptaAviso,
        onAvisoChange = { vm.aceptaAviso = it },
        errorAviso = vm.intentoEnviar && vm.errorAviso,
        onEnviar = { vm.enviar() },
        onNueva = { vm.limpiar() }
    )
}