package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class Pantalla { INICIO, QUIZ, RESULTADO }
data class Pregunta(val texto: String, val opciones: List<String>, val correcta: Int)

class QuizViewModel : ViewModel() {
    val preguntas = listOf(
        Pregunta("¿Qué tipo de dato guarda 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
        Pregunta("¿Qué palabra clave crea una variable mutable en Kotlin?", listOf("val", "var", "const", "let"), 1),
        Pregunta("¿Qué estructura evalúa múltiples casos?", listOf("for", "when", "while", "try"), 1),
        Pregunta("¿Qué significa null safety en Kotlin?", listOf("No hay variables", "Control de nulos", "Sin bucles", "Solo enteros"), 1),
        Pregunta("¿Qué función convierte texto a entero seguro?", listOf("toInt()", "toIntOrNull()", "parseInt()", "asInt()"), 1)
    )

    var pantalla by mutableStateOf(Pantalla.INICIO)
    var nombre by mutableStateOf("")
    var indicePregunta by mutableStateOf(0)
    var seleccionada by mutableStateOf<Int?>(null)
    var respondida by mutableStateOf(false)
    var puntaje by mutableStateOf(0)

    fun comenzar() {
        if (nombre.length >= 2) {
            indicePregunta = 0
            puntaje = 0
            seleccionada = null
            respondida = false
            pantalla = Pantalla.QUIZ
        }
    }

    fun confirmar() {
        if (seleccionada != null && !respondida) {
            respondida = true
            if (seleccionada == preguntas[indicePregunta].correcta) {
                puntaje++
            }
        }
    }

    fun siguiente() {
        if (indicePregunta < preguntas.size - 1) {
            indicePregunta++
            seleccionada = null
            respondida = false
        } else {
            pantalla = Pantalla.RESULTADO
        }
    }

    fun reintentar() {
        indicePregunta = 0
        puntaje = 0
        seleccionada = null
        respondida = false
        pantalla = Pantalla.QUIZ
    }

    fun cambiarJugador() {
        nombre = ""
        indicePregunta = 0
        puntaje = 0
        seleccionada = null
        respondida = false
        pantalla = Pantalla.INICIO
    }
}