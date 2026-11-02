package com.example.textfield.Screen

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfield.Components.Fuente
import com.example.textfield.Components.Interlineado
import com.example.textfield.Components.Sinter
import com.example.textfield.Components.modoOscuro
import com.example.textfield.Components.slider

@Composable
fun lectura(modifier: Modifier= Modifier){
    var tam by remember { mutableStateOf(18f)}
    var interlineado by remember{ mutableStateOf(Interlineado.NORMAL)}
    var oscuro by remember { mutableStateOf(false)}
    var esSerif by remember { mutableStateOf(false)}
    val fondoOscuro = if (oscuro) Color(0xFF121212) else Color.White
    val textoOscuro = if (oscuro) Color.White else Color.Black
Column(modifier = Modifier. fillMaxSize().verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(16.dp)) {
    Box(Modifier
        .fillMaxWidth()
        .background(fondoOscuro)
        .padding(16.dp))
    {
        Text(text="Cuando Gregorio Samsa despertó aquella mañana, luego de un sueño agitado, se encontró en su cama convertido en un insecto monstruoso." +
                " Estaba echado sobre el quitinoso caparazón de su espalda, y al levantar un poco la cabeza," +
                " vio la figura convexa de su vientre oscuro, surcado por curvadas durezas," +
                " cuya prominencia apenas si podía aguantar la colcha," +
                " visiblemente a punto de escurrirse hasta el suelo. Innumerables patas," +
                " lamentablemente escuálidas en comparación con el grosor ordinario de sus piernas," +
                " ofrecían a sus ojos el espectáculo de una agitación sin consistencia",
            color = textoOscuro,
            fontSize = tam.sp,
            lineHeight = (tam * interlineado.factor).sp,
            fontFamily = if (esSerif) FontFamily.Serif else FontFamily.SansSerif
        )
    }
    slider(tam = tam, onTamChange = {tam = it})
    Sinter(seleccionado = interlineado, onSeleccionar = {interlineado =it})
    modoOscuro(Oscuro =  oscuro, onOscuroChange = {oscuro = it})
    Fuente(esSerif= esSerif, onFuenteChange = {esSerif = it})
    Button(
        onClick = {
            tam = 18f
            interlineado = Interlineado.NORMAL
            oscuro = false
            esSerif = false
        },
        modifier=Modifier.fillMaxWidth()
    ) {
        Text("Restablecer")
    }
}


}