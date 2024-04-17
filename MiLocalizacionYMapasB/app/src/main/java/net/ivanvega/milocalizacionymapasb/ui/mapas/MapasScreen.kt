package net.ivanvega.milocalizacionymapasb.ui.mapas

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MiPrimerMapa() {
    val singapore = LatLng(19.42709, -99.16765)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
    }

    Box(Modifier.fillMaxSize()) {



        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "Mexico City",
                snippet = "Marker in Angel de la independencia"
            )

        }

        Switch(
            checked = uiSettings.zoomControlsEnabled,
            onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
            }
        )
    }
    DibujarMapa()
}
@Composable
fun DibujarMapa() {
    var activado by remember { mutableStateOf(false) }
    var markerPosition by remember { mutableStateOf(LatLng(0.0, 0.0)) } // Posición inicial del marcador

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("DEMO_MAP_ID")
            },
            onMapClick = { latLng ->
                if (activado) {
                    markerPosition = latLng // Actualizar la posición del marcador al hacer clic en el mapa
                }
            }
        ) {
            if (activado) {
                // Mostrar el marcador solo si está activado
                AdvancedMarker(
                    state = MarkerState(position = markerPosition),
                    title = "Marcador"
                )
            }
        }
        Button(
            onClick = { activado = !activado },
            Modifier.align(Alignment.BottomStart)
        ) {
            Text(text = if (activado) "Desactivar" else "Activar")
        }
    }
}
