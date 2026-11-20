package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.CuentaHoist
import com.example.textfield.Components.CuentaViewModel

@Composable
fun CuentaScreen(vm: CuentaViewModel = viewModel()) {
    CuentaHoist(
        montoTexto = vm.montoTexto,
        onMontoChange = { vm.montoTexto = it },
        propinaPct = vm.propinaPct,
        onPropinaChange = { vm.propinaPct = it },
        usaOtro = vm.usaOtro,
        onUsaOtroChange = { vm.usaOtro = it },
        personas = vm.personas,
        onPersonasChange = { vm.personas = it },
        redondear = vm.redondear,
        onRedondearChange = { vm.redondear = it },
        propina = vm.propina,
        total = vm.total,
        porPersona = vm.porPersona,
        sobrante = vm.sobrante
    )
}