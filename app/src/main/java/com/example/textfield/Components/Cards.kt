package com.example.textfield.Components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuizHoist(
    pantalla: Pantalla,
    nombre: String,
    onNombreChange: (String) -> Unit,
    onComenzar: () -> Unit,
    pregunta: Pregunta,
    numero: Int,
    total: Int,
    seleccionada: Int?,
    respondida: Boolean,
    onSeleccionar: (Int) -> Unit,
    onConfirmar: () -> Unit,
    onSiguiente: () -> Unit,
    puntaje: Int,
    onReintentar: () -> Unit,
    onCambiarJugador: () -> Unit
) {
    when (pantalla) {
        Pantalla.INICIO -> InicioQuiz(
            nombre = nombre,
            onNombreChange = onNombreChange,
            onComenzar = onComenzar
        )

        Pantalla.QUIZ -> PreguntaQuiz(
            pregunta = pregunta,
            numero = numero,
            total = total,
            seleccionada = seleccionada,
            respondida = respondida,
            onSeleccionar = onSeleccionar,
            onConfirmar = onConfirmar,
            onSiguiente = onSiguiente
        )

        Pantalla.RESULTADO -> ResultadoQuiz(
            nombre = nombre,
            puntaje = puntaje,
            total = total,
            onReintentar = onReintentar,
            onCambiarJugador = onCambiarJugador
        )
    }
}

@Composable
fun InicioQuiz(nombre: String, onNombreChange: (String) -> Unit, onComenzar: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Quiz de programación")
        TextField(value = nombre, onValueChange = onNombreChange, label = { Text("Tu nombre") })
        Button(onClick = onComenzar, enabled = nombre.length >= 2) { Text("Comenzar") }
    }
}

@Composable
fun PreguntaQuiz(
    pregunta: Pregunta,
    numero: Int,
    total: Int,
    seleccionada: Int?,
    respondida: Boolean,
    onSeleccionar: (Int) -> Unit,
    onConfirmar: () -> Unit,
    onSiguiente: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Pregunta $numero de $total")
        LinearProgressIndicator(progress = { numero / total.toFloat() })
        Text(pregunta.texto)

        pregunta.opciones.forEachIndexed { i, op ->
            val marca = if (respondida && i == pregunta.correcta) " (Correcta)" else ""
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = seleccionada == i, onClick = { if (!respondida) onSeleccionar(i) })
                Text("$op$marca")
            }
        }

        if (!respondida) {
            Button(onClick = onConfirmar, enabled = seleccionada != null) { Text("Confirmar") }
        } else {
            val textoBtn = if (numero == total) "Ver resultados" else "Siguiente"
            Button(onClick = onSiguiente) { Text(textoBtn) }
        }
    }
}

@Composable
fun ResultadoQuiz(nombre: String, puntaje: Int, total: Int, onReintentar: () -> Unit, onCambiarJugador: () -> Unit) {
    val msg = when (puntaje) {
        in 0..2 -> "Sigue practicando"
        in 3..4 -> "Buen trabajo"
        else -> "Perfecto"
    }
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("¡Muy bien, $nombre! $puntaje de $total")
        Text(msg)
        Button(onClick = onReintentar) { Text("Reintentar") }
        Button(onClick = onCambiarJugador) { Text("Cambiar jugador") }
    }
}