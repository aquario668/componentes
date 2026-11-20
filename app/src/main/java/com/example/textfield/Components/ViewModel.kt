package com.example.textfield.Components
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class Prioridad { ALTA, MEDIA, BAJA }
enum class Filtro { TODAS, PENDIENTES, HECHAS }
data class Tarea(val id: Int, val titulo: String, val prioridad: Prioridad, val hecha: Boolean = false)

class TareasViewModel : ViewModel() {
    val tareas = mutableStateListOf<Tarea>()
    var textoNueva by mutableStateOf("")
    var prioridadNueva by mutableStateOf(Prioridad.MEDIA)
    var filtro by mutableStateOf(Filtro.TODAS)
    var mostrarDialogo by mutableStateOf(false)

    val tareasVisibles: List<Tarea>
        get() = tareas.filter {
            when (filtro) {
                Filtro.TODAS -> true
                Filtro.PENDIENTES -> !it.hecha
                Filtro.HECHAS -> it.hecha
            }
        }

    val pendientes: Int
        get() = tareas.count { !it.hecha }

    fun agregarTarea() {
        if (textoNueva.isNotBlank()) {
            tareas.add(Tarea(tareas.size + 1, textoNueva, prioridadNueva))
            textoNueva = ""
        }
    }

    fun toggleTarea(id: Int, hecha: Boolean) {
        val idx = tareas.indexOfFirst { it.id == id }
        if (idx != -1) tareas[idx] = tareas[idx].copy(hecha = hecha)
    }

    fun eliminarTarea(tarea: Tarea) {
        tareas.remove(tarea)
    }

    fun borrarCompletadas() {
        tareas.removeAll { it.hecha }
        mostrarDialogo = false
    }
}