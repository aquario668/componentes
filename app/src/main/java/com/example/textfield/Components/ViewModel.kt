package com.example.textfield.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class CarreraViewModel : ViewModel() {
    val meta = 100
    val nombres = listOf("Rojo", "Azul", "Verde")
    val avances = mutableStateListOf(0, 0, 0)

    var corriendo by mutableStateOf(false)
    var ganador by mutableStateOf<String?>(null)

    private var jobCarrera: Job? = null

    fun iniciarCarrera() {
        if (corriendo) return
        jobCarrera?.cancel()
        avances.fill(0)
        ganador = null
        corriendo = true

        jobCarrera = viewModelScope.launch {
            val jobs = nombres.indices.map { i ->
                launch {
                    while (avances[i] < meta && ganador == null) {
                        delay(Random.nextLong(100, 300))
                        if (ganador == null) {
                            val paso = Random.nextInt(5, 20)
                            val nuevo = (avances[i] + paso).coerceAtMost(meta)
                            avances[i] = nuevo
                            if (nuevo >= meta && ganador == null) {
                                ganador = nombres[i]
                            }
                        }
                    }
                }
            }
            jobs.forEach { it.join() }
            corriendo = false
        }
    }
}