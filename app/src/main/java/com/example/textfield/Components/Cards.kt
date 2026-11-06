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
import androidx.compose.material3.Divider
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp


@Composable
fun StepperPersonas(personas: Int, onChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { if (personas > 1) onChange(personas - 1) }) {
            Text("-", style = MaterialTheme.typography.titleLarge)
        }
        Text("$personas", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 16.dp))
        IconButton(onClick = { if (personas < 20) onChange(personas + 1) }) {
            Text("+", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun ResultadoCuenta(
    propina: Double,
    total: Double,
    porPersona: Double,
    sobrante: Double
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Propina total", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(String.format("$%.2f", propina))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total con propina", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(String.format("$%.2f", total), style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Cada quien paga", style = MaterialTheme.typography.bodyLarge)
        Text(
            String.format("$%.2f", porPersona),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary
        )
        if (sobrante > 0) {
            Text("Sobrante por redondeo: ${String.format("$%.2f", sobrante)}", style = MaterialTheme.typography.bodySmall)
        }
    }
}