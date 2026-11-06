package com.example.textfield.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun RatingBar(titulo: String, rating: Int, onRatingChange: (Int) -> Unit) {
    Column {
        Text(titulo)
        Row {
            (1..5).forEach { estrella ->
                IconButton(onClick = {
                    onRatingChange(if (rating == estrella) 0 else estrella)
                }) {
                    Text(if (estrella <= rating) "★" else "☆", color = if (estrella <= rating) Color.Yellow else Color.Gray)
                }
            }
        }
    }
}

@Composable
fun ComentarioField(texto: String, onChange: (String) -> Unit, maximo: Int = 200) {
    Column {
        TextField(
            value = texto,
            onValueChange = { if (it.length <= maximo) onChange(it) }
        )
        Text(
            "${texto.length}/$maximo",
            color = if (texto.length >= 180) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ResumenResena(general: Double, comentario: String, etiquetas: Set<String>) {
    Column {
        Text("Reseña publicada con éxito")
        Text("Promedio: ${String.format("%.1f", general)}")
        Text("Comentario: $comentario")
        Text("Etiquetas: ${etiquetas.joinToString()}")
    }
}