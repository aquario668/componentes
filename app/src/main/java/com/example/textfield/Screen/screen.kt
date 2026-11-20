package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.AforoHoist
import com.example.textfield.Components.AforoViewModel

@Composable
fun AforoScreen(vm: AforoViewModel = viewModel()) {
    AforoHoist(
        actuales = vm.actuales,
        aforoMax = vm.aforoMax,
        porcentaje = vm.porcentaje,
        porcentajeTexto = vm.porcentajeTexto,
        estadoTexto = vm.estadoTexto,
        visitasDelDia = vm.visitasDelDia,
        onEntra = { vm.entra() },
        onSale = { vm.sale() },
        onCerrarDia = { vm.cerrarDia() }
    )
}