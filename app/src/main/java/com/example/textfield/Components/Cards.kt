package com.example.textfield.Components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResenaHoist(
    publicada: Boolean,
    comida: Int,
    onComidaChange: (Int) -> Unit,
    servicio: Int,
    onServicioChange: (Int) -> Unit,
    ambiente: Int,
    onAmbienteChange: (Int) -> Unit,
    todasCalificadas: Boolean,
    general: Double,
    comentario: String,
    onComentarioChange: (String) -> Unit,
    etiquetas: Set<String>,
    onToggleEtiqueta: (String) -> Unit,
    puedePublicar: Boolean,
    onPublicar: () -> Unit
) {
    if (publicada) {
        ResumenResena(general = general, comentario = comentario, etiquetas = etiquetas)
    } else {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            RatingBar("Comida", comida, onComidaChange)
            RatingBar("Servicio", servicio, onServicioChange)
            RatingBar("Ambiente", ambiente, onAmbienteChange)

            if (todasCalificadas) {
                Text("Calificación general: ${String.format("%.1f", general)}")
            }

            TextField(value = comentario, onValueChange = onComentarioChange, label = { Text("Comentario") })
            Text("${comentario.length}/200")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Familiar", "Romántico", "Económico", "Vegetariano", "Rápido").forEach { e ->
                    FilterChip(selected = e in etiquetas, onClick = { onToggleEtiqueta(e) }, label = { Text(e) })
                }
            }

            Button(onClick = onPublicar, enabled = puedePublicar) { Text("Publicar") }
        }
    }
}

@Composable
fun RatingBar(titulo: String, rating: Int, onRatingChange: (Int) -> Unit) {
    Column {
        Text(titulo)
        Row {
            (1..5).forEach { n ->
                IconButton(onClick = { onRatingChange(n) }) {
                    Text(if (n <= rating) "★" else "☆")
                }
            }
        }
    }
}

@Composable
fun ResumenResena(general: Double, comentario: String, etiquetas: Set<String>) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Reseña publicada")
        Text("Promedio: ${String.format("%.1f", general)}")
        Text("Comentario: $comentario")
        Text("Etiquetas: ${etiquetas.joinToString()}")
    }
}