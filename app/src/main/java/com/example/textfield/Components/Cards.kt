package com.example.textfield.Components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CuentaHoist(
    montoTexto: String,
    onMontoChange: (String) -> Unit,
    propinaPct: Int,
    onPropinaChange: (Int) -> Unit,
    usaOtro: Boolean,
    onUsaOtroChange: (Boolean) -> Unit,
    personas: Int,
    onPersonasChange: (Int) -> Unit,
    redondear: Boolean,
    onRedondearChange: (Boolean) -> Unit,
    propina: Double,
    total: Double,
    porPersona: Double,
    sobrante: Double
) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Cuenta dividida")
        TextField(value = montoTexto, onValueChange = onMontoChange, label = { Text("Monto") })

        SelectorPropina(
            porcentaje = propinaPct,
            usaOtro = usaOtro,
            onPorcentajeChange = onPropinaChange,
            onOtroChange = onUsaOtroChange
        )

        StepperPersonas(personas = personas, onChange = onPersonasChange)

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("Redondear hacia arriba")
            Switch(checked = redondear, onCheckedChange = onRedondearChange)
        }

        Divider()
        ResultadoCuenta(propina = propina, total = total, porPersona = porPersona, sobrante = sobrante, redondear = redondear)
    }
}

@Composable
fun SelectorPropina(
    porcentaje: Int,
    usaOtro: Boolean,
    onPorcentajeChange: (Int) -> Unit,
    onOtroChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(10, 15, 20).forEach { pct ->
                FilterChip(
                    selected = !usaOtro && porcentaje == pct,
                    onClick = { onOtroChange(false); onPorcentajeChange(pct) },
                    label = { Text("$pct %") }
                )
            }
            FilterChip(selected = usaOtro, onClick = { onOtroChange(true) }, label = { Text("Otro") })
        }

        if (usaOtro) {
            Slider(value = porcentaje.toFloat(), onValueChange = { onPorcentajeChange(it.toInt()) }, valueRange = 0f..30f)
            Text("Propina: $porcentaje %")
        }
    }
}

@Composable
fun StepperPersonas(personas: Int, onChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text("Personas")
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (personas > 1) onChange(personas - 1) }) { Text("-") }
            Text("$personas")
            IconButton(onClick = { if (personas < 20) onChange(personas + 1) }) { Text("+") }
        }
    }
}

@Composable
fun ResultadoCuenta(propina: Double, total: Double, porPersona: Double, sobrante: Double, redondear: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text("Propina: ${String.format("$%.2f", propina)}")
        Text("Total: ${String.format("$%.2f", total)}")
        Text("Por persona: ${String.format("$%.2f", porPersona)}")
        if (redondear) Text("Sobrante: ${String.format("$%.2f", sobrante)}")
    }
}