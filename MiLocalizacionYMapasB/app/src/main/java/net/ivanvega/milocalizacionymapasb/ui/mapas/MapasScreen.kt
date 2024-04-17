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
    var marker1Position by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var marker2Position by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var marker1Activated by remember { mutableStateOf(false) }
    var marker2Activated by remember { mutableStateOf(false) }
    var drawingEnabled by remember { mutableStateOf(false) } // Nuevo estado para habilitar/deshabilitar la función de dibujo

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("DEMO_MAP_ID")
            },
            onMapClick = { latLng ->
                if (drawingEnabled) {
                    if (marker1Activated && marker2Activated) {
                        // Si ambos marcadores están activados, no hacer nada
                    } else if (marker1Activated) {
                        // Si el primer marcador está activado, actualizar su posición
                        marker1Position = sas
                        marker2Activated = true
                    } else {
                        // Si no, actualizar la posición del segundo marcador
                        marker2Position = latLng
                        marker1Activated = true
                    }
                }
            }
        ) {
            if (marker1Activated) {
                // Mostrar el primer marcador si está activado
                AdvancedMarker(
                    state = MarkerState(position = marker1Position),
                    title = "Marcador 1"
                )
            }
            if (marker2Activated) {
                // Mostrar el segundo marcador si está activado
                AdvancedMarker(
                    state = MarkerState(position = marker2Position),
                    title = "Marcador 2"
                )
            }
        }
        Button(
            onClick = {
                drawingEnabled = !drawingEnabled // Alternar entre habilitar y deshabilitar la función de dibujo
                if (!drawingEnabled) {
                    // Si la función de dibujo está desactivada, restablecer la activación de los marcadores y sus posiciones
                    marker1Activated = false
                    marker2Activated = false
                    marker1Position = LatLng(0.0, 0.0)
                    marker2Position = LatLng(0.0, 0.0)
                }
            },
            Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = if (drawingEnabled) "Desactivar dibujo" else "Activar dibujo")
        }
    }
}

