package com.example.textfield.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.example.textfield.Screen.Pregunta


@Composable
fun InicioScreen(nombre: String, onNombreChange: (String) -> Unit, onComenzar: () -> Unit) {
    Column {
        TextField(value = nombre, onValueChange = onNombreChange, label = { Text("Tu nombre") })
        Button(onClick = onComenzar, enabled = nombre.length >= 2) { Text("Comenzar") }
    }
}

@Composable
fun PreguntaScreen(
    pregunta: Pregunta, numero: Int, total: Int, seleccionada: Int?,
    respondida: Boolean, onSeleccionar: (Int) -> Unit,
    onConfirmar: () -> Unit, onSiguiente: () -> Unit
) {
    Column {
        Text("Pregunta $numero de $total")
        Text(pregunta.texto)

        pregunta.opciones.forEachIndexed { i, texto ->
            Row {
                RadioButton(selected = seleccionada == i, onClick = { if (!respondida) onSeleccionar(i) })
                Text(texto, color = if (respondida && i == pregunta.correcta) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
            }
        }

        if (!respondida) {
            Button(onClick = onConfirmar, enabled = seleccionada != null) { Text("Confirmar") }
        } else {
            Button(onClick = onSiguiente) { Text(if (numero == total) "Ver resultados" else "Siguiente") }
        }
    }
}

@Composable
fun ResultadoScreen(nombre: String, puntaje: Int, total: Int, onReintentar: () -> Unit, onCambiarJugador: () -> Unit) {
    Column {
        Text("¡Muy bien, $nombre! $puntaje de $total")
        Button(onClick = onReintentar) { Text("Reintentar") }
        Button(onClick = onCambiarJugador) { Text("Cambiar jugador") }
    }
}