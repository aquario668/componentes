package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.textfield.Components.ComentarioField
import com.example.textfield.Components.RatingBar
import com.example.textfield.Components.ResumenResena


@Composable
fun ResenaScreen() {
    var comida by remember { mutableStateOf(0) }
    var servicio by remember { mutableStateOf(0) }
    var ambiente by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }
    var etiquetas by remember { mutableStateOf(setOf<String>()) }
    var publicada by remember { mutableStateOf(false) }

    val todasCalificadas = comida > 0 && servicio > 0 && ambiente > 0
    val general = if (todasCalificadas) (comida + servicio + ambiente) / 3.0 else 0.0
    val puedePublicar = todasCalificadas && comentario.length >= 20
Column(modifier = Modifier.fillMaxSize().background(Color.White) , horizontalAlignment = Alignment.CenterHorizontally ) {
    Text("Restaurante El Mezquite", fontSize = 16.sp)
    if (publicada) {
        ResumenResena(general = general, comentario = comentario, etiquetas = etiquetas)
    } else {
        Column {
            RatingBar(titulo = "Comida", rating = comida, onRatingChange = { comida = it })
            RatingBar(titulo = "Servicio", rating = servicio, onRatingChange = { servicio = it })
            RatingBar(titulo = "Ambiente", rating = ambiente, onRatingChange = { ambiente = it })

            if (todasCalificadas) Text("Calificación general: ${String.format("%.1f", general)}")

            ComentarioField(texto = comentario, onChange = { comentario = it })

            Row {
                listOf("Familiar", "Romántico", "Económico").forEach { e ->
                    FilterChip(
                        selected = e in etiquetas,
                        onClick = {
                            etiquetas = if (e in etiquetas) etiquetas - e else etiquetas + e
                        },
                        label = { Text(e) }
                    )
                }
            }

            Button(onClick = { publicada = true }, enabled = puedePublicar) { Text("Publicar") }
        }
    }
}
}