package com.example.textfield.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.textfield.Screen.Categoria
import com.example.textfield.Screen.Gasto


@Composable
fun ResumenPresupuesto(totalGastado: Double, saldo: Double, porcentaje: Double) {
    Column {
        Text("Gastado: $totalGastado | Saldo: $saldo")
        if (porcentaje >= 1.0) {
            Text("Excediste el presupuesto", color = MaterialTheme.colorScheme.error)
        } else if (porcentaje >= 0.8) {
            Text("Cuidado: pasaste el 80%")
        }
    }
}

@Composable
fun FormularioGasto(onAgregar: (String, Double, Categoria) -> Unit) {
    // Estado local, no ensucia la pantalla principal
    var concepto by remember { mutableStateOf("") }
    var montoTexto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf(Categoria.COMIDA) }

    val monto = montoTexto.toDoubleOrNull()
    val valido = concepto.isNotBlank() && monto != null && monto > 0

    Column {
        TextField(value = concepto, onValueChange = { concepto = it }, label = { Text("Concepto") })
        TextField(value = montoTexto, onValueChange = { montoTexto = it }, label = { Text("Monto") })
        Button(
            onClick = {
                onAgregar(concepto, monto!!, categoria)
                concepto = ""; montoTexto = ""
            },
            enabled = valido
        ) { Text("Agregar gasto") }
    }
}

@Composable
fun ListaGastos(gastos: List<Gasto>, onEliminar: (Gasto) -> Unit) {
    Column {
        gastos.forEach { gasto ->
            Row {
                Text("${gasto.concepto} - $${gasto.monto}")
                IconButton(onClick = { onEliminar(gasto) }) { Text("x") }
            }
        }
    }
}