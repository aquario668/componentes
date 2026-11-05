package com.example.textfield.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.textfield.Components.registroCorrecto

@Composable
fun lectura(modifier: Modifier= Modifier) {
   var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var taller by remember { mutableStateOf("Seleccionar...") }
    var aceptarAviso by remember { mutableStateOf(false) }
    var intentoEnviar by remember { mutableStateOf(false) }
    var inscrito by remember{mutableStateOf(false)}
    var menuExpandido by remember { mutableStateOf((false)) }


    val errorNombre =nombre.length<3
    val errorCorreo = !correo.contains("@") || !correo.contains(".")
    val edadNum = edad.toIntOrNull() ?:0
    val errorEdad= edadNum !in 12..99
    val errorTaller =taller == "Seleccionar..."
    val errorAviso =!aceptarAviso
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        if (inscrito) {
            registroCorrecto(
                nombre = nombre,
                correo = correo,
                taller = taller,
                onNueva = {
                    nombre = ""
                    correo = ""
                    edad = ""
                    taller = "Seleccionar..."
                    aceptarAviso = false
                    intentoEnviar = false
                    inscrito = false
                }
            )
        } else {
            Column(
                modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre Completo") },
                    isError = intentoEnviar && errorNombre
                )
                if (intentoEnviar && errorNombre) Text("Minimo 3 caracteres para el nombre", color = Color.Red)

                TextField(
                    value =correo,
                    onValueChange = {correo=it},
                    label = {Text("Correo")},
                    isError = intentoEnviar && errorCorreo
                )
                if(intentoEnviar && errorCorreo) Text("falta el @ o un .", color = Color.Red)
                TextField(
                    value = edad,
                    onValueChange = {edad = it},
                    label ={Text("edad")},
                    isError = intentoEnviar && errorEdad
                )
                if(intentoEnviar && errorEdad) Text("la edad debe estar entre 12 y 99", color = Color.Red)

                Box{
                    Button(onClick = {menuExpandido=true}) {
                        Text("taller:$taller")
                    }



                DropdownMenu(expanded = menuExpandido, onDismissRequest = {menuExpandido = false}) {
                    DropdownMenuItem(text={Text("Pintura")} , onClick = {taller = "Pintura"; menuExpandido=false})
                    DropdownMenuItem(text = {Text("Fotografia")} , onClick = {taller = "Fotografia"; menuExpandido=false})
                    DropdownMenuItem(text = {Text("Guitarra")}, onClick = {taller= "Guitarra" ; menuExpandido=false })
                } }
                if(intentoEnviar && errorTaller) Text("elige un taller", color = Color.Red)
                Row{
                    Checkbox(checked = aceptarAviso,onCheckedChange = {aceptarAviso= it})
                            Text("Acepto el aviso de privacidad")
                }
                if (intentoEnviar && errorAviso) Text("debes aceptar el aviso", color = Color.Red)
                Spacer(modifier = Modifier.height(16.dp))
                Button( onClick = {
                    intentoEnviar=true
                    if (!errorAviso && !errorTaller && !errorEdad && !errorCorreo && !errorNombre){
                        inscrito = true
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                    Text("Inscribirme")
                }





            }
        }
    }




















}