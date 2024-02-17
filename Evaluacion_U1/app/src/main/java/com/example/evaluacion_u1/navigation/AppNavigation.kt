package com.example.evaluacion_u1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.evaluacion_u1.ui.theme.screens.Usuario
import com.example.evaluacion_u1.ui.theme.screens.mostrarDatos

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen"){
        composable(route ="login_screen"){ Usuario(navController = navController) }
        composable(route="datos_screen"){ mostrarDatos(navController = navController)}
    }

}