package net.ivanvega.milocalizacionymapasb.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ivanvega.milocalizacionymapasb.ui.mapas.CrearMapas2
import net.ivanvega.milocalizacionymapasb.ui.mapas.CustomigMarkersInfo
import net.ivanvega.milocalizacionymapasb.ui.mapas.DrawingMap
import net.ivanvega.milocalizacionymapasb.ui.mapas.MiPrimerMapa
import net.ivanvega.milocalizacionymapasb.ui.mapas.RecoposingeElements
import net.ivanvega.milocalizacionymapasb.ui.mapas.StreetView

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "PrimerMapa") {
        composable(route = "primermapa") { MiPrimerMapa(navController = navController) }
        composable(route = "dibujarmapa"){ DrawingMap(navController = navController)}
        composable(route = "streeview"){ StreetView(navController = navController)}
        composable(route = "mapa2"){CrearMapas2(navController = navController)}
        composable(route = "pais"){RecoposingeElements(navController = navController)}

    }
}