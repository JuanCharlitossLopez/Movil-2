package com.example.evaluacion_u1.ui.theme.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.data.RetrofitClient
import com.example.evaluacion_u1.model.CalificacionUnidad
import com.example.evaluacion_u1.model.Envelope4
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getCalifByUnit(
    context: Context,
    navController: NavController,
    viewModel: DataViewModel
) {
    val service = RetrofitClient(context).retrofitService4
    val bodyAcademico = CalifByUniRequestBody()
    service.getAllCalifbyUnidad(bodyAcademico).enqueue(object : Callback<Envelope4> {
        override fun onResponse(call: Call<Envelope4>, response: Response<Envelope4>) {
            if (response.isSuccessful) {
                val envelope = response.body()
                val alumnoResultJson: String? =
                    envelope?.body4?.getCalifUnidadesByAlumnoResponse?.getCalifUnidadesByAlumnoResult

                // Deserializa la cadena JSON
                val json = Json { ignoreUnknownKeys = true }
                val califPorUnit: List<CalificacionUnidad>? = json.decodeFromString(alumnoResultJson ?: "")

//                // Verifica si la deserialización fue exitosa antes de continuar
//                if (califPorUnit != null) {
//                    Log.d("exito", "se obtuvo el perfil 2: ${califPorUnit}")
//                    val califUnitResultJson = Json.encodeToJsonElement(califPorUnit)
//                    viewModel.califXunit = califPorUnit
//                    navController.navigate("califByUnidad")
//                } else {
//                    // Maneja el caso en que la deserialización falló
//                    showError(context, "Error en la deserialización del perfil académico")
//                }
                Log.w("exito", "se obtuvo el perfil 2: ${califPorUnit}")
                val califUnitResultJson = Json.encodeToJsonElement(califPorUnit)
                viewModel.califXunit = califPorUnit
                navController.navigate("califByUnidad")
            } else {
                showError(
                    context,
                    "Error al obtener el perfil académico. Código de respuesta: ${response.code()}"
                )
            }
        }
        override fun onFailure(call: Call<Envelope4>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud del perfil académico")

        }
    })
}

private fun CalifByUniRequestBody(): RequestBody {
    return """
        <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getCalifUnidadesByAlumno xmlns="http://tempuri.org/" />
  </soap:Body>
</soap:Envelope>
        """.trimIndent().toRequestBody("text/xml; charset=utf-8".toMediaTypeOrNull())
}

private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun mostrarCalifByUnit(navController: NavController, viewModel: DataViewModel) {
    val calificacionUnidad = viewModel.califXunit

    if (calificacionUnidad != null) {
        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(vertical = 1.dp)
                .size(1050.dp, 200.dp)
        ) {
            Text(
                text = "Materia: ${calificacionUnidad.lastIndex}"
            )

        }
    }
}