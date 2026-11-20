package com.example.textfield.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LecturaHoist(
    texto: String,
    tamano: Float,
    onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    usaSerif: Boolean,
    onUsaSerifChange: (Boolean) -> Unit,
    onRestablecer: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Ajustes de lectura")
        ControlesLectura(
            tamano = tamano,
            onTamanoChange = onTamanoChange,
            interlineado = interlineado,
            onInterlineadoChange = onInterlineadoChange,
            modoOscuro = modoOscuro,
            onModoOscuroChange = onModoOscuroChange,
            usaSerif = usaSerif,
            onUsaSerifChange = onUsaSerifChange
        )
        Divider()
        VistaPrevia(
            texto = texto,
            tamano = tamano,
            interlineado = interlineado,
            modoOscuro = modoOscuro,
            usaSerif = usaSerif
        )
        OutlinedButton(onClick = onRestablecer, modifier = Modifier.fillMaxWidth()) { Text("Restablecer") }
    }
}

@Composable
fun ControlesLectura(
    tamano: Float,
    onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    usaSerif: Boolean,
    onUsaSerifChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Tamaño de letra: ${tamano.toInt()} sp")
        Slider(value = tamano, onValueChange = onTamanoChange, valueRange = 14f..32f)

        Text("Interlineado")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Interlineado.entries.forEach { opcion ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = interlineado == opcion, onClick = { onInterlineadoChange(opcion) })
                    Text(opcion.etiqueta)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Modo oscuro")
            Switch(checked = modoOscuro, onCheckedChange = onModoOscuroChange)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = usaSerif, onClick = { onUsaSerifChange(true) }, label = { Text("Serif") })
            FilterChip(selected = !usaSerif, onClick = { onUsaSerifChange(false) }, label = { Text("Sans") })
        }
    }
}

@Composable
fun VistaPrevia(
    texto: String,
    tamano: Float,
    interlineado: Interlineado,
    modoOscuro: Boolean,
    usaSerif: Boolean
) {
    val fondo = if (modoOscuro) Color.DarkGray else Color.LightGray
    val letra = if (modoOscuro) Color.White else Color.Black
    val fuente = if (usaSerif) FontFamily.Serif else FontFamily.SansSerif

    Box(modifier = Modifier.fillMaxWidth().background(fondo).padding(16.dp)) {
        Text(
            text = texto,
            color = letra,
            fontSize = tamano.sp,
            lineHeight = (tamano * interlineado.factor).sp,
            fontFamily = fuente
        )
    }
}