package com.example.evaluacion_u1.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.R


@Composable
fun mostrarDatos(navController: NavController, viewModel: DataViewModel) {
    val alumnoAcademicoResult = viewModel.alumnoAcademicoResult
    Image(
        painter = painterResource(id = R.drawable.fondodate),
        contentDescription = "My background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    if (alumnoAcademicoResult != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Card(modifier = Modifier.padding(30.dp).size(600.dp,45.dp)){
                Text(text = "Nombre: ${alumnoAcademicoResult.nombre}",modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier.padding(30.dp).size(600.dp,45.dp)){
                Text(text = "Matrícula: ${alumnoAcademicoResult.matricula}",modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterHorizontally))
            }

            Card(modifier = Modifier.padding(30.dp).size(600.dp,45.dp)){
                Text(text = "Carrera: ${alumnoAcademicoResult.carrera}",modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier.padding(30.dp).size(600.dp,45.dp)){
                Text(text = "Semestre Actual: ${alumnoAcademicoResult.semActual}",modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier.padding(30.dp).size(600.dp,65.dp)){
                Text(text = "Especialidad: ${alumnoAcademicoResult.especialidad}",modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterHorizontally))
            }
            // Agrega más campos aquí
            Spacer(modifier = Modifier.padding(30.dp))
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { navController.popBackStack() }) {
                Text(text = "Cerrar sesion")
            }
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "No se pudo obtener el perfil académico.")
        }
    }
}



