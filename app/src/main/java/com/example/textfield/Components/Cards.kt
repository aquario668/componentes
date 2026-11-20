package com.example.textfield.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarritoHoist(
    catalogo: List<Producto>,
    cantidades: List<Int>,
    totalArticulos: Int,
    subtotal: Double,
    descuento: Double,
    envio: Double,
    total: Double,
    onCantidadChange: (Int, Int) -> Unit,
    onVaciar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Tienda Don Pepe")

        catalogo.forEachIndexed { i, prod ->
            ProductoItem(
                producto = prod,
                cantidad = cantidades[i],
                onCantidadChange = { onCantidadChange(i, it) }
            )
        }

        Divider()
        ResumenCarrito(totalArticulos, subtotal, descuento, envio, total)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = onVaciar) { Text("Vaciar") }
            Button(onClick = {}, enabled = totalArticulos > 0) { Text("Confirmar pedido") }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, cantidad: Int, onCantidadChange: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(producto.nombre)
            Text("$${producto.precio} c/u")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onCantidadChange(cantidad - 1) }) { Text("-") }
            Text("$cantidad")
            IconButton(onClick = { onCantidadChange(cantidad + 1) }) { Text("+") }
        }
    }
}

@Composable
fun ResumenCarrito(articulos: Int, subtotal: Double, descuento: Double, envio: Double, total: Double) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Artículos: $articulos")
        Text("Subtotal: ${String.format("$%.2f", subtotal)}")
        if (descuento > 0) Text("Descuento 10%: ${String.format("-$%.2f", descuento)}")
        val textoEnvio = if (envio == 0.0 && articulos > 0) "GRATIS" else String.format("$%.2f", envio)
        Text("Envío: $textoEnvio")
        Text("TOTAL: ${String.format("$%.2f", total)}")
    }
}