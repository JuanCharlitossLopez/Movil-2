package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState


@Composable
fun DrawingMap(navController: NavController){
    GoogleMap(
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("DEMO_MAP_ID")
        },
        //...
    ) {
        AdvancedMarker(
            state = MarkerState(position = LatLng(-34.1, 151.1)),
            title = "Marker in Sydney"
        )
        AdvancedMarker(
            state = MarkerState(position = LatLng(35.66, 139.6)),
            title = "Marker in Tokyo"
        )
    }
}