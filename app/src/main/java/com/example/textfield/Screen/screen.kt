package com.example.textfield.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var numeroTexto by remember { mutableStateOf("") }
    val ytext = "B"

    Column(modifier = modifier.padding(16.dp).fillMaxSize()) {
        Palabra(
            repetir = numeroTexto,
            onRepetirChange = { nuevoTexto ->
                numeroTexto = nuevoTexto
            },
            textoMostrar = ytext
        )
    }
}

@Composable
fun Palabra(
    repetir: String,
    onRepetirChange: (String) -> Unit,
    textoMostrar: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextField(
            value = repetir,
            onValueChange = onRepetirChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )



            val cantidad = repetir.toIntOrNull() ?: 0
            repeat(cantidad) {
                Text(text = textoMostrar)
            }
        }
    }
