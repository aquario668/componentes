package com.example.textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.textfield.Screen.InicioScreen
import com.example.textfield.ui.theme.TextfieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextfieldTheme {
                InicioScreen(
                    textoIngresado = "Elemento de prueba",
                    elementos = listOf("MANZANA", "PAN", "ARROZ"),
                    onTextoChange = {},
                    onAgregar = {}
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    InicioScreen(
        textoIngresado = "Elemento de prueba",
        elementos = listOf("MANZANA", "PAN", "ARROZ"),
        onTextoChange = {},
        onAgregar = {}
    )
}