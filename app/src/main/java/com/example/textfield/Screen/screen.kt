package com.example.textfield.Screen

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfield.Components.card

@Composable
fun aforo(modifier : Modifier){
    var actuales by rememberSaveable{mutableStateOf(0) }
    var totales by remember{mutableStateOf(50)}
    var visitas by rememberSaveable{mutableStateOf(0) }
    val porcentaje = if (totales>0) (actuales*100) / totales else 0
    val progreso = if (totales >0 ) actuales.toFloat() / totales.toFloat() else 0f
    //
    val indpro = when{
        porcentaje < 70 -> Color.Green
       porcentaje <100 -> Color.Yellow
        else -> Color.Red

    }
    Column(Modifier.fillMaxSize().background(Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = actuales.toString() + "/" + totales.toString(),
                fontSize = 32.sp
            )
        Spacer(Modifier.height(16.dp))
        Text( text = "$porcentaje % de ocupacion",
            color = indpro)
        Spacer(Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = progreso,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(16.dp)
                .clip(RoundedCornerShape(50)),
            color = indpro,
            trackColor = Color.LightGray


        )
        Spacer(Modifier.height(16.dp))
        Row() {
            Button(
                onClick = { actuales++ ; visitas++ },
                enabled = actuales < 50,
                modifier = Modifier.padding(20.dp)

            ) {
                Text("+ ENTRA")
            }
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { actuales--},
                enabled = actuales > 0,
                modifier = Modifier.padding(20.dp),
            ) {
                Text("- SALE")
            }

        }
        Spacer(Modifier.height(60.dp))
        Text(
            text = "Visitas del dia:"+visitas,
            fontSize = 32.sp
        )
        Spacer(Modifier.height(60.dp))
        Button(onClick = {actuales=0 ; visitas=0}) {
            Text("Reinciar el dia")
        }

        }
}
