package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.textfield.Components.FormularioGasto
import com.example.textfield.Components.ListaGastos
import com.example.textfield.Components.ResumenPresupuesto


enum class Categoria { COMIDA, TRANSPORTE, RENTA, OCIO, OTROS }
data class Gasto(val id: Int, val concepto: String, val monto: Double, val categoria: Categoria)

@Composable
fun PresupuestoScreen() {
    var ingresoTexto by remember { mutableStateOf("4500") }
    val gastos = remember { mutableStateListOf<Gasto>() }

    val ingreso = ingresoTexto.toDoubleOrNull() ?: 0.0
    val totalGastado = gastos.sumOf { it.monto }
    val saldo = ingreso - totalGastado
    val porcentaje = if (ingreso > 0) totalGastado / ingreso else 0.0

    Column(modifier = Modifier.fillMaxSize().background(Color.White), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(value = ingresoTexto, onValueChange = { ingresoTexto = it }, label = { Text("Ingreso mensual") })

        ResumenPresupuesto(totalGastado = totalGastado, saldo = saldo, porcentaje = porcentaje)
        FormularioGasto(
            onAgregar = { concepto, monto, categoria ->
                gastos.add(Gasto(gastos.size, concepto, monto, categoria))
            }
        )

        ListaGastos(gastos = gastos, onEliminar = { gastos.remove(it) })
    }
}