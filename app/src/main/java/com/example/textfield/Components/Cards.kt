package com.example.textfield.Components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp




@Composable
fun registroCorrecto(
   nombre:String,
   correo:String,
   taller: String,
   onNueva:()-> Unit
){
    Column(modifier = Modifier.padding(16.dp) ) {
        Text("Inscripcion exitosa")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nombre :$nombre")
        Text("correo:$correo")
        Text("Taller:$taller")
        Spacer(modifier=Modifier.height(16.dp))
        Button(onClick = onNueva) {
            Text("Nueva Inscripcion")
        }

    }

}