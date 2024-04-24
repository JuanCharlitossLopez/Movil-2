package net.ivanvega.milocalizacionymapasb

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.platform.location.locationupdates.LocationUpdatesScreen
import com.example.platform.location.permission.LocationPermissionScreen
import net.ivanvega.milocalizacionymapasb.Navegacion.AppNavegacion
import net.ivanvega.milocalizacionymapasb.ui.location.CurrentLocationScreen
import net.ivanvega.milocalizacionymapasb.ui.mapas.CrearMapas2
import net.ivanvega.milocalizacionymapasb.ui.mapas.DrawingMap
import net.ivanvega.milocalizacionymapasb.ui.mapas.GoogleMapView
import net.ivanvega.milocalizacionymapasb.ui.mapas.MiPrimerMapa
import net.ivanvega.milocalizacionymapasb.ui.mapas.RecoposingeElements
import net.ivanvega.milocalizacionymapasb.ui.mapas.StreetView
import net.ivanvega.milocalizacionymapasb.ui.theme.MiLocalizacionYMapasBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiLocalizacionYMapasBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        //LocationPermissionScreen()
                        //CurrentLocationScreen()
                        //LocationUpdatesScreen()

                        //MiPrimerMapa()
                       //DrawingMap()
                       //RecoposingeElements()
                      // CurrentLocationScreen()
                        //StreetView()
                        //CrearMapas2()
                        //GoogleMapView()
                        AppNavegacion()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiLocalizacionYMapasBTheme {
        Greeting("Android")
    }
}



