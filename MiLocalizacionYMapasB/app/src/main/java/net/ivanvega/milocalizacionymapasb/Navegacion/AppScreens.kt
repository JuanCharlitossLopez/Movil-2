package net.ivanvega.milocalizacionymapasb.Navegacion

sealed class AppScreens(val route: String) {
    object PrimerMap : AppScreens(route = "primermapa")
    
    object DrawingMap : AppScreens(route = "dibujarmapa")

    object Streeview : AppScreens(route = "streeview")

    object Mapa2Screen : AppScreens(route = "mapa2")

    object CustomigMarkersInfo : AppScreens(route = "noc")

    object RecoposingeElements: AppScreens(route = "pais")

}