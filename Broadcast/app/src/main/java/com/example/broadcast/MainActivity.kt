package com.example.broadcast

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.broadcast.ui.theme.BroadcastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BroadcastTheme {
                // crear el contexto
                val context = LocalContext.current
                var text by remember { mutableStateOf("Initital configuration") }
                //
                DisposableEffect(context, Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    val receiver = AirplaneModeReceiver(
                        onEnabled = { text = "Airplane mode enabled" },
                        onDisabled = { text = "Airplane mode disabled" }
                    )
                    receiver.register(context)
                    onDispose {
                        receiver.unregister(context)
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyContent(text = text)
                }
            }
        }
    }
}

//Mostrar en pantalla
@Composable
fun MyContent(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
    }
}