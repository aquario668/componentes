package com.example.textfield.Components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarreraHoist(
    nombres: List<String>,
    avances: List<Int>,
    meta: Int,
    corriendo: Boolean,
    ganador: String?,
    onIniciar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Carrera de corrutinas")

        nombres.forEachIndexed { i, nombre ->
            CorredorItem(
                nombre = nombre,
                avance = avances[i],
                meta = meta
            )
        }

        if (ganador != null) {
            Text("Ganador: $ganador")
        }

        Button(
            onClick = onIniciar,
            enabled = !corriendo,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (corriendo) "Corriendo..." else "Iniciar carrera")
        }
    }
}

@Composable
fun CorredorItem(nombre: String, avance: Int, meta: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("$nombre: $avance / $meta")
        LinearProgressIndicator(
            progress = { avance / meta.toFloat() },
            modifier = Modifier.fillMaxWidth()
        )
    }
}