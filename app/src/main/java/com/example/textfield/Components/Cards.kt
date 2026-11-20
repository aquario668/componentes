package com.example.textfield.Components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PresupuestoHoist(
    ingresoTexto: String,
    onIngresoChange: (String) -> Unit,
    ingreso: Double,
    totalGastado: Double,
    saldo: Double,
    porcentaje: Double,
    categoriaTop: String,
    gastos: List<Gasto>,
    onAgregarGasto: (String, Double, Categoria) -> Unit,
    onEliminarGasto: (Gasto) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextField(value = ingresoTexto, onValueChange = onIngresoChange, label = { Text("Ingreso mensual") })
        Text("Gastado: $totalGastado | Saldo: $saldo")
        Text("Mayor gasto: $categoriaTop")

        if (porcentaje >= 1.0) {
            Text("Excedido por: ${totalGastado - ingreso}")
        } else if (porcentaje >= 0.8) {
            Text("Cuidado: pasaste el 80 %")
        }

        FormularioGasto(onAgregar = onAgregarGasto)

        gastos.forEach { gasto ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${gasto.concepto} - $${gasto.monto} (${gasto.categoria.name})")
                IconButton(onClick = { onEliminarGasto(gasto) }) { Text("x") }
            }
        }
    }
}

@Composable
fun FormularioGasto(onAgregar: (String, Double, Categoria) -> Unit) {
    var concepto by remember { mutableStateOf("") }
    var montoTexto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf(Categoria.COMIDA) }
    var menuAbierto by remember { mutableStateOf(false) }

    val monto = montoTexto.toDoubleOrNull()
    val valido = concepto.isNotBlank() && monto != null && monto > 0

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextField(value = concepto, onValueChange = { concepto = it }, label = { Text("Concepto") })
        TextField(value = montoTexto, onValueChange = { montoTexto = it }, label = { Text("Monto") })

        Box {
            Button(onClick = { menuAbierto = true }) { Text(categoria.name) }
            DropdownMenu(expanded = menuAbierto, onDismissRequest = { menuAbierto = false }) {
                Categoria.entries.forEach { cat ->
                    DropdownMenuItem(text = { Text(cat.name) }, onClick = { categoria = cat; menuAbierto = false })
                }
            }
        }

        Button(
            onClick = {
                onAgregar(concepto, monto!!, categoria)
                concepto = ""
                montoTexto = ""
            },
            enabled = valido
        ) {
            Text("Agregar gasto")
        }
    }
}