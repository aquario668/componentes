package com.example.textfield.Screen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.ResenaHoist
import com.example.textfield.Components.ResenaViewModel

@Composable
fun ResenaScreen(vm: ResenaViewModel = viewModel()) {
    ResenaHoist(
        publicada = vm.publicada,
        comida = vm.comida,
        onComidaChange = { vm.toggleComida(it) },
        servicio = vm.servicio,
        onServicioChange = { vm.toggleServicio(it) },
        ambiente = vm.ambiente,
        onAmbienteChange = { vm.toggleAmbiente(it) },
        todasCalificadas = vm.todasCalificadas,
        general = vm.general,
        comentario = vm.comentario,
        onComentarioChange = { vm.actualizarComentario(it) },
        etiquetas = vm.etiquetas,
        onToggleEtiqueta = { vm.toggleEtiqueta(it) },
        puedePublicar = vm.puedePublicar,
        onPublicar = { vm.publicada = true }
    )
}