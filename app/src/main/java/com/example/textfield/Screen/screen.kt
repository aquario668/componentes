package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.TareasHoist
import com.example.textfield.Components.TareasViewModel

@Composable
fun TareasScreen(vm: TareasViewModel = viewModel()) {
    TareasHoist(
        textoNueva = vm.textoNueva,
        onTextoChange = { vm.textoNueva = it },
        prioridadNueva = vm.prioridadNueva,
        onPrioridadChange = { vm.prioridadNueva = it },
        filtro = vm.filtro,
        onFiltroChange = { vm.filtro = it },
        tareasVisibles = vm.tareasVisibles,
        pendientes = vm.pendientes,
        totalTareas = vm.tareas.size,
        mostrarDialogo = vm.mostrarDialogo,
        onMostrarDialogo = { vm.mostrarDialogo = it },
        onAgregar = { vm.agregarTarea() },
        onToggleTarea = { id, hecha -> vm.toggleTarea(id, hecha) },
        onEliminarTarea = { vm.eliminarTarea(it) },
        onConfirmarBorrado = { vm.borrarCompletadas() }
    )
}