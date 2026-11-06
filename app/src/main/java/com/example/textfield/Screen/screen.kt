package com.example.textfield.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.textfield.Components.ConfirmarBorradoDialog
import com.example.textfield.Components.TareaItem


enum class Prioridad { ALTA, MEDIA, BAJA }
enum class Filtro { TODAS, PENDIENTES, HECHAS }
data class Tarea(val id: Int, val titulo: String, val prioridad: Prioridad, val hecha: Boolean = false)

@Composable
fun TareasScreen() {
    val tareas = remember { mutableStateListOf<Tarea>() }
    var textoNueva by remember { mutableStateOf("") }
    var prioridadNueva by remember { mutableStateOf(Prioridad.MEDIA) }
    var filtro by remember { mutableStateOf(Filtro.TODAS) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    val tareasVisibles = tareas.filter {
        when (filtro) {
            Filtro.TODAS -> true
            Filtro.PENDIENTES -> !it.hecha
            Filtro.HECHAS -> it.hecha
        }
    }
    val pendientes = tareas.count { !it.hecha }

    if (mostrarDialogo) {
        ConfirmarBorradoDialog(
            cantidad = tareas.count { it.hecha },
            onConfirm = {
                tareas.removeAll { it.hecha }
                mostrarDialogo = false
            },
            onDismiss = { mostrarDialogo = false }
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Mis pendientes", style = MaterialTheme.typography.headlineMedium)

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = textoNueva,
                onValueChange = { textoNueva = it },
                label = { Text("Nueva tarea") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    tareas.add(Tarea(tareas.size, textoNueva, prioridadNueva))
                    textoNueva = ""
                },
                enabled = textoNueva.isNotBlank(),
                modifier = Modifier.padding(start = 8.dp)
            ) { Text("+") }
        }
        Row {
            Filtro.values().forEach { f ->
                FilterChip(selected = filtro == f, onClick = { filtro = f }, label = { Text(f.name) })
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tareasVisibles, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onCheckedChange = { valor ->
                        val index = tareas.indexOfFirst { it.id == tarea.id }
                        if (index != -1) tareas[index] = tareas[index].copy(hecha = valor)
                    },
                    onDelete = { tareas.remove(tarea) }
                )
            }
        }

        Text("$pendientes pendientes de ${tareas.size}", modifier = Modifier.padding(vertical = 8.dp))
        Button(onClick = { mostrarDialogo = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Borrar completadas")
        }
    }
}