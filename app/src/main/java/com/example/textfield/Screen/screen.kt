package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.QuizHoist
import com.example.textfield.Components.QuizViewModel

@Composable
fun QuizScreen(vm: QuizViewModel = viewModel()) {
    QuizHoist(
        pantalla = vm.pantalla,
        nombre = vm.nombre,
        onNombreChange = { vm.nombre = it },
        onComenzar = { vm.comenzar() },
        pregunta = vm.preguntas[vm.indicePregunta],
        numero = vm.indicePregunta + 1,
        total = vm.preguntas.size,
        seleccionada = vm.seleccionada,
        respondida = vm.respondida,
        onSeleccionar = { vm.seleccionada = it },
        onConfirmar = { vm.confirmar() },
        onSiguiente = { vm.siguiente() },
        puntaje = vm.puntaje,
        onReintentar = { vm.reintentar() },
        onCambiarJugador = { vm.cambiarJugador() }
    )
}