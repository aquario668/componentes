package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class Categoria { COMIDA, TRANSPORTE, RENTA, OCIO, OTROS }
data class Gasto(val id: Int, val concepto: String, val monto: Double, val categoria: Categoria)

class PresupuestoViewModel : ViewModel() {
    var ingresoTexto by mutableStateOf("4500")
    val gastos = mutableStateListOf<Gasto>()

    val ingreso: Double
        get() = ingresoTexto.toDoubleOrNull() ?: 0.0

    val totalGastado: Double
        get() = gastos.sumOf { it.monto }

    val saldo: Double
        get() = ingreso - totalGastado

    val porcentaje: Double
        get() = if (ingreso > 0) totalGastado / ingreso else 0.0

    val categoriaTop: String
        get() {
            val top = gastos.groupBy { it.categoria }.maxByOrNull { (_, lista) -> lista.sumOf { it.monto } }
            return top?.key?.name ?: "Ninguna"
        }

    fun agregarGasto(concepto: String, monto: Double, categoria: Categoria) {
        gastos.add(Gasto(gastos.size + 1, concepto, monto, categoria))
    }

    fun eliminarGasto(gasto: Gasto) {
        gastos.remove(gasto)
    }
}