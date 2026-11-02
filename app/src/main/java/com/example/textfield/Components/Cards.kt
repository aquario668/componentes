package com.example.textfield.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp



enum class Interlineado(val etiqueta: String , val factor: Float){
    COMPACTO("Compacto", 1.0f),
    NORMAL("Normal", 1.3f),
    AMPLIO("Amplio", 1.6f)
}
@Composable
fun slider(
    tam: Float,
    onTamChange: (Float)-> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier) {
        Text("tamano de letra: ${tam.toInt()} sp")
        Slider(
            value = tam,
            onValueChange = onTamChange,
            valueRange = 14f..32f,
            steps = 17
        )
    }
}
@Composable
fun Sinter(
    seleccionado: Interlineado,
    onSeleccionar: (Interlineado) -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier.selectableGroup()) {
        Text("Interlineado")
        Interlineado.entries.forEach{ opcion ->
            Row(
                Modifier.fillMaxWidth().selectable(
                    selected = opcion ==seleccionado,
                    onClick = {onSeleccionar(opcion)},
                    role = Role.RadioButton

                )
                    .padding(vertical =16.dp),
                 verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = opcion ==seleccionado, onClick = null)
                Spacer(Modifier.width(8.dp))
                Text("${opcion.etiqueta} (${opcion.factor})")
            }

        }
    }

}
@Composable
fun modoOscuro(
    Oscuro: Boolean,
    onOscuroChange: (Boolean) -> Unit,
    modifier:Modifier = Modifier
){
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text("Modo Oscuro", Modifier.weight(1f))
        Switch(checked = Oscuro, onCheckedChange = onOscuroChange)

    }
}

@Composable
fun Fuente(
    esSerif: Boolean,
    onFuenteChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(
            selected = esSerif,
            onClick = { onFuenteChange(true) },
            label = { Text("Serif") }
        )
        FilterChip(
            selected = !esSerif,
            onClick = { onFuenteChange(false) },
            label = { Text("Sans") }
        )
    }
}


