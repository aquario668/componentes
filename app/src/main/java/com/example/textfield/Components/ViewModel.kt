package com.example.textfield.Components

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Producto(val nombre: String, val precio: Double)

class CarritoViewModel : ViewModel() {
    val catalogo = listOf(
        Producto("Tortillas 1 kg", 24.00),
        Producto("Leche 1 L", 27.50),
        Producto("Huevo 12 pz", 52.00),
        Producto("Frijol 1 kg", 38.00)
    )
    val cantidades = mutableStateListOf(0, 0, 0, 0)

    val totalArticulos: Int
        get() = cantidades.sum()

    val subtotal: Double
        get() = catalogo.indices.sumOf { catalogo[it].precio * cantidades[it] }

    val descuento: Double
        get() = if (subtotal >= 200.0) subtotal * 0.10 else 0.0

    val envio: Double
        get() {
            val subDesc = subtotal - descuento
            return if (totalArticulos == 0) 0.0 else if (subDesc >= 150.0) 0.0 else 25.0
        }

    val total: Double
        get() = (subtotal - descuento) + envio

    fun cambiarCantidad(index: Int, nueva: Int) {
        if (nueva in 0..10) cantidades[index] = nueva
    }

    fun vaciar() {
        cantidades.fill(0)
    }
}