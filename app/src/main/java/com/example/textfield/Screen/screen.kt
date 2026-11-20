package com.example.textfield.Screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textfield.Components.LecturaHoist
import com.example.textfield.Components.LecturaViewModel

@Composable
fun LecturaScreen(vm: LecturaViewModel = viewModel()) {
    val textoEjemplo = "Cuando Gregorio Samsa despertó aquella mañana, luego de un sueño agitado, se encontró en su cama convertido en un insecto monstruoso." +
            " Estaba echado sobre el quitinoso caparazón de su espalda, y al levantar un poco la cabeza," +
            " vio la figura convexa de su vientre oscuro, surcado por curvadas durezas," +
            " cuya prominencia apenas si podía aguantar la colcha," +
            " visiblemente a punto de escurrirse hasta el suelo. Innumerables patas," +
            " lamentablemente escuálidas en comparación con el grosor ordinario de sus piernas," +
            " ofrecían a sus ojos el espectáculo de una agitación sin consistencia"

    LecturaHoist(
        texto = textoEjemplo,
        tamano = vm.tamano,
        onTamanoChange = { vm.tamano = it },
        interlineado = vm.interlineado,
        onInterlineadoChange = { vm.interlineado = it },
        modoOscuro = vm.modoOscuro,
        onModoOscuroChange = { vm.modoOscuro = it },
        usaSerif = vm.usaSerif,
        onUsaSerifChange = { vm.usaSerif = it },
        onRestablecer = { vm.restablecer() }
    )
}