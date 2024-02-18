package com.example.evaluacion_u1.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.navigation.AppScreens


@Composable
fun mostrarDatos(navController: NavController, viewModel: DataViewModel) {
    //BodyContent(navController)
    val alumnoAcademicoResult = viewModel.alumnoAcademicoResult
    // Verifica si alumnoAcademicoResult es null
    if (alumnoAcademicoResult != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${alumnoAcademicoResult.nombre}")
            Text(text = "Matrícula: ${alumnoAcademicoResult.matricula}")
            Text(text = "Carrera: ${alumnoAcademicoResult.carrera}")
            Text(text = "Semestre Actual: ${alumnoAcademicoResult.semActual}")
            Text(text = "Semestre Actual: ${alumnoAcademicoResult.lineamiento}")
            // Agrega más campos aquí
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "No se pudo obtener el perfil académico.")
        }
    }
}


@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Data Screen")
        Button(onClick = { navController.navigate(AppScreens.loginSice.route) }) {
            Text(text = "Cerrar sesion")
        }
    }
}