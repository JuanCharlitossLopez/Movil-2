package com.example.mymesajeria


import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permisos concedidos
            Log.d("body", "granted")
        } else {
            // Permission denegados
            Log.d("permission", "denied")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se llama al método para solicitar permisos de notificación.
        askNotificationPermission()
        // Se obtiene el token de registro de Firebase Cloud Messaging (FCM).
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // Si no se pudo obtener el token, se imprime un mensaje de log con el error.
                    Log.d("FCM Notify", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Si se obtuvo el token, se muestra en un Toast y se imprime un mensaje de log.
                val token: String? = task.result
                Log.d("FCM Token", token, task.exception)
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })

        setContent {

            Greeting("token")

        }

    }
    // Método para solicitar permisos de notificación.
    private fun askNotificationPermission() {
        // Verifica si se necesita solicitar permisos de notificación en dispositivos con Android 13 o superior.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // Si el permiso ya está concedido, no se hace nada.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                //Aqui se puede agregar más contenido
            } else {
                // Preguntar directamente los permisos
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "App de notificaciones:  $name!",
        modifier = modifier
    )
}