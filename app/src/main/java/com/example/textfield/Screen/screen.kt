package com.example.textfield.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.textfield.Components.ResultadoCuenta
import com.example.textfield.Components.StepperPersonas
import kotlin.math.ceil


@Composable
fun CuentaScreen() {

    var montoTexto by remember { mutableStateOf("") }
    var propinaPct by remember { mutableStateOf(10) }
    var personas by remember { mutableStateOf(1) }
    var redondear by remember { mutableStateOf(false) }
    val monto = montoTexto.toDoubleOrNull() ?: 0.0
    val propina = monto * (propinaPct / 100.0)
    val total = monto + propina
    val pagoExacto = if (personas > 0) total / personas else 0.0
    val porPersona = if (redondear) ceil(pagoExacto) else pagoExacto
    val sobrante = if (redondear) (porPersona * personas) - total else 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Cuenta dividida", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = montoTexto,
            onValueChange = { montoTexto = it },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Propina")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(10, 15, 20).forEach { pct ->
                FilterChip(
                    selected = propinaPct == pct,
                    onClick = { propinaPct = pct },
                    label = { Text("$pct %") }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Personas")
            StepperPersonas(
                personas = personas,
                onChange = { personas = it }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Redondear hacia arriba")
            Switch(checked = redondear, onCheckedChange = { redondear = it })
        }

        Divider()

        ResultadoCuenta(
            propina = propina,
            total = total,
            porPersona = porPersona,
            sobrante = sobrante
        )
    }
}