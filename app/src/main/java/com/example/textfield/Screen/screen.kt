package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.CarritoHoist
import com.example.textfield.Components.CarritoViewModel

@Composable
fun CarritoScreen(vm: CarritoViewModel = viewModel()) {
    CarritoHoist(
        catalogo = vm.catalogo,
        cantidades = vm.cantidades,
        totalArticulos = vm.totalArticulos,
        subtotal = vm.subtotal,
        descuento = vm.descuento,
        envio = vm.envio,
        total = vm.total,
        onCantidadChange = { i, c -> vm.cambiarCantidad(i, c) },
        onVaciar = { vm.vaciar() }
    )
}