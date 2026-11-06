package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.textfield.Components.InicioScreen
import com.example.textfield.Components.PreguntaScreen
import com.example.textfield.Components.ResultadoScreen


enum class Pantalla { INICIO, QUIZ, RESULTADO }
data class Pregunta(val texto: String, val opciones: List<String>, val correcta: Int)

@Composable
fun QuizApp() {
    var pantalla by remember { mutableStateOf(Pantalla.INICIO) }
    var nombre by remember { mutableStateOf("") }
    var indicePregunta by remember { mutableStateOf(0) }
    var seleccionada by remember { mutableStateOf<Int?>(null) }
    var respondida by remember { mutableStateOf(false) }
    var puntaje by remember { mutableStateOf(0) }

    val preguntas = listOf(
        Pregunta("¿Qué tipo de dato usar para 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
        // ... (resto de preguntas omitidas por brevedad)
    )
    Column(modifier = Modifier.fillMaxSize().background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
    when (pantalla) {
        Pantalla.INICIO -> {
            InicioScreen(
                nombre = nombre,
                onNombreChange = { nombre = it },
                onComenzar = { pantalla = Pantalla.QUIZ }
            )
        }
        Pantalla.QUIZ -> {
            PreguntaScreen(
                pregunta = preguntas[indicePregunta],
                numero = indicePregunta + 1,
                total = preguntas.size,
                seleccionada = seleccionada,
                respondida = respondida,
                onSeleccionar = { seleccionada = it },
                onConfirmar = {
                    respondida = true
                    if (seleccionada == preguntas[indicePregunta].correcta) puntaje++
                },
                onSiguiente = {
                    if (indicePregunta < preguntas.size - 1) {
                        indicePregunta++
                        seleccionada = null
                        respondida = false
                    } else {
                        pantalla = Pantalla.RESULTADO
                    }
                }
            )
        }
        Pantalla.RESULTADO -> {
            ResultadoScreen(
                nombre = nombre, puntaje = puntaje, total = preguntas.size,
                onReintentar = {
                    indicePregunta = 0; puntaje = 0; seleccionada = null; respondida = false
                    pantalla = Pantalla.QUIZ
                },
                onCambiarJugador = {
                    nombre = ""; indicePregunta = 0; puntaje = 0; seleccionada = null; respondida = false
                    pantalla = Pantalla.INICIO
                }
            )
        }
    }}
}