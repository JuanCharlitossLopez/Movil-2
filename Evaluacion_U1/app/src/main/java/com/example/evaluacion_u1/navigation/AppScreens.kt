package com.example.evaluacion_u1.navigation

sealed class AppScreens (val route: String){
    object loginSice: AppScreens(route = "login_screen")
    object dataScreen: AppScreens(route= "data_screen")
}