package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent

@Composable
fun CustomigMarkersInfo(navController: NavController){
    MarkerInfoWindowContent(
        //...
    ) { marker ->
        Text(marker.title ?: "Default Marker Title", color = Color.Red)
    }

    MarkerInfoWindow(
        //...
    ) { marker ->
        // Implement the custom info window here
        Column {
            Text(marker.title ?: "Default Marker Title", color = Color.Red)
            Text(marker.snippet ?: "Default Marker Snippet", color = Color.Red)
        }
    }

}