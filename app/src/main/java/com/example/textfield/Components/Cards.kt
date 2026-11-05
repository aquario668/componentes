package com.example.textfield.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp



data class  Producto ( val nombre: String , val precio: Double)

@Composable
fun productoItem(
  producto: Producto,
  cantidad: Int,
  onCantidadChange:(Int)-> Unit
){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column{
            Text(producto.nombre)
            Text("$${producto.precio} c/u")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {if (cantidad>0) onCantidadChange(cantidad-1)}) {
                Text("-")
            }
            Text("$cantidad", modifier = Modifier.padding(horizontal = 8.dp))
            IconButton(onClick = {if (cantidad <10) onCantidadChange(cantidad+1) }
            ) {  Text("+")}
        }
    }
}


@Composable
fun ResumenCarrito(
    articulos: Int,
    subtotal: Double,
    descuento: Double,
    envio: Double,
    total: Double
){
Column(verticalArrangement = Arrangement.spacedBy(8.dp) , modifier = Modifier.background(Color.Gray)) {

    ResumenFila("Articulos", "$articulos")
    ResumenFila("Subtotal", String.format("$%.2f", subtotal))
    if(descuento >0){
        ResumenFila("Descuento 10%", String.format("-$%.2f", descuento))
    }
    val textoEnvio = if (envio == 0.0 && articulos>0 )"Gratis" else String.format("$%.2f",envio)
    ResumenFila("Envío", textoEnvio, color = if (envio == 0.0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
    Divider(modifier=Modifier.padding(vertical = 8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Total")
        Text(String.format("$%.2f",total))
    }
}
}


@Composable
fun ResumenFila(etiqueta: String , valor : String , color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface){
Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween) {
    Text(etiqueta, color = color)
    Text(valor, color = color)
}

}

