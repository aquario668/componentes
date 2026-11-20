package com.example.textfield.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TareasHoist(
    textoNueva: String,
    onTextoChange: (String) -> Unit,
    prioridadNueva: Prioridad,
    onPrioridadChange: (Prioridad) -> Unit,
    filtro: Filtro,
    onFiltroChange: (Filtro) -> Unit,
    tareasVisibles: List<Tarea>,
    pendientes: Int,
    totalTareas: Int,
    mostrarDialogo: Boolean,
    onMostrarDialogo: (Boolean) -> Unit,
    onAgregar: () -> Unit,
    onToggleTarea: (Int, Boolean) -> Unit,
    onEliminarTarea: (Tarea) -> Unit,
    onConfirmarBorrado: () -> Unit
) {
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { onMostrarDialogo(false) },
            title = { Text("¿Borrar completadas?") },
            text = { Text("Se borrarán las tareas completadas.") },
            confirmButton = { TextButton(onClick = onConfirmarBorrado) { Text("Borrar") } },
            dismissButton = { TextButton(onClick = { onMostrarDialogo(false) }) { Text("Cancelar") } }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Mis pendientes")

        Row {
            OutlinedTextField(value = textoNueva, onValueChange = onTextoChange, label = { Text("Nueva tarea") }, modifier = Modifier.weight(1f))
            Button(onClick = onAgregar, enabled = textoNueva.isNotBlank()) { Text("+") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Prioridad.entries.forEach { p ->
                FilterChip(selected = prioridadNueva == p, onClick = { onPrioridadChange(p) }, label = { Text(p.name) })
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Filtro.entries.forEach { f ->
                FilterChip(selected = filtro == f, onClick = { onFiltroChange(f) }, label = { Text(f.name) })
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tareasVisibles, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onCheckedChange = { onToggleTarea(tarea.id, it) },
                    onDelete = { onEliminarTarea(tarea) }
                )
            }
        }

        Text("$pendientes pendientes de $totalTareas")
        Button(onClick = { onMostrarDialogo(true) }, modifier = Modifier.fillMaxWidth()) { Text("Borrar completadas") }
    }
}

@Composable
fun TareaItem(tarea: Tarea, onCheckedChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Checkbox(checked = tarea.hecha, onCheckedChange = onCheckedChange)
        Text("${tarea.titulo} (${tarea.prioridad.name})", modifier = Modifier.weight(1f))
        IconButton(onClick = onDelete) { Text("x") }
    }
}