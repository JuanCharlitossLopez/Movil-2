package net.ivanvega.milocalizacionymapasb.ui.mapas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun RecoposingeElements(){
    val singapore = LatLng(1.3521, 103.8198) // Latitud y longitud
    val markerState = rememberMarkerState(position = singapore)


    LaunchedEffect(Unit) {
        repeat(10) {
            delay(5.seconds)
            val old = markerState.position
            markerState.position = LatLng(old.latitude + 1.0, old.longitude + 2.0)
        }
    }
}