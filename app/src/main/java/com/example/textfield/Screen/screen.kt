package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.CarreraHoist
import com.example.textfield.Components.CarreraViewModel

@Composable
fun CarreraScreen(vm: CarreraViewModel = viewModel() ) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        CarreraHoist(
            nombres = vm.nombres,
            avances = vm.avances,
            meta = vm.meta,
            corriendo = vm.corriendo,
            ganador = vm.ganador,
            onIniciar = { vm.iniciarCarrera() }
        )
    }
}