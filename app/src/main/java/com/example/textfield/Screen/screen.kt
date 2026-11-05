package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.textfield.Components.Producto
import com.example.textfield.Components.ResumenCarrito
import com.example.textfield.Components.productoItem

@Composable
fun carrito() {

    val catalogo = remember {
        listOf(
            Producto("Tortillas 1 kg", 24.00),
            Producto("Leche 1 L", 27.50),
            Producto("Huevo 12 pz", 52.00),
            Producto("Frijol 1 kg", 38.00)
        )
    }
    val cantidades = remember { mutableStateListOf(0, 0, 0, 0) }
    val totalArticulos = cantidades.sum()
    val subtotal = catalogo.indices.sumOf { catalogo[it].precio * cantidades[it] }
    val descuento = if (subtotal >= 200.0) subtotal * 0.10 else 0.0
    val subtotalConDescuento = subtotal - descuento
    val envio = if (totalArticulos == 0) 0.0 else if (subtotalConDescuento >= 150.0) 0.0 else 25.0
    val total = subtotalConDescuento + envio

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Tienda Don Pepe", style = MaterialTheme.typography.headlineMedium)
        catalogo.forEachIndexed { index, producto ->
            productoItem (
                producto = producto,
                cantidad = cantidades[index],
                onCantidadChange = { nuevaCantidad -> cantidades[index] = nuevaCantidad }
            )
        }
        Divider()
        ResumenCarrito(
            articulos = totalArticulos,
            subtotal = subtotal,
            descuento = descuento,
            envio = envio,
            total = total
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { cantidades.fill(0) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Vaciar")
            }
            Button(
                onClick = { },
                enabled = totalArticulos > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Confirmar pedido")
            }
        }
    }
}