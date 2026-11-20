package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.PresupuestoHoist
import com.example.textfield.Components.PresupuestoViewModel

@Composable
fun PresupuestoScreen(vm: PresupuestoViewModel = viewModel()) {
    PresupuestoHoist(
        ingresoTexto = vm.ingresoTexto,
        onIngresoChange = { vm.ingresoTexto = it },
        ingreso = vm.ingreso,
        totalGastado = vm.totalGastado,
        saldo = vm.saldo,
        porcentaje = vm.porcentaje,
        categoriaTop = vm.categoriaTop,
        gastos = vm.gastos,
        onAgregarGasto = { c, m, cat -> vm.agregarGasto(c, m, cat) },
        onEliminarGasto = { vm.eliminarGasto(it) }
    )
}