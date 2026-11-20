package com.example.textfield.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AforoHoist(
    actuales: Int,
    aforoMax: Int,
    porcentaje: Float,
    porcentajeTexto: Int,
    estadoTexto: String,
    visitasDelDia: Int,
    onEntra: () -> Unit,
    onSale: () -> Unit,
    onCerrarDia: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Biblioteca Central")
        Text("Sala de estudio · capacidad $aforoMax")
        Text("$actuales / $aforoMax")
        Text("personas en la sala")
        LinearProgressIndicator(progress = { porcentaje }, modifier = Modifier.fillMaxWidth())
        Text("$porcentajeTexto % de ocupación")
        Text(estadoTexto)

        ControlesAforo(
            actuales = actuales,
            aforoMax = aforoMax,
            onEntra = onEntra,
            onSale = onSale
        )

        Divider()
        Text("Visitas del día: $visitasDelDia")
        Button(onClick = onCerrarDia, modifier = Modifier.fillMaxWidth()) { Text("Cerrar día") }
    }
}

@Composable
fun ControlesAforo(
    actuales: Int,
    aforoMax: Int,
    onEntra: () -> Unit,
    onSale: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedButton(onClick = onSale, enabled = actuales > 0) { Text("- Sale") }
        Button(onClick = onEntra, enabled = actuales < aforoMax) { Text("+ Entra") }
    }
}