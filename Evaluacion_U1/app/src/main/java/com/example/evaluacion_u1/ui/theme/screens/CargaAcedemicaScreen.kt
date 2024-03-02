package com.example.evaluacion_u1.ui.theme.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.data.RetrofitClient
import com.example.evaluacion_u1.model.CargaAcademicaItem
import com.example.evaluacion_u1.model.Envelope2
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CargaAcademica(navController: NavController, viewModel: DataViewModel) {
    val CargaByAlumno = viewModel.CargaByAlumno

    if (CargaByAlumno != null) {
        Column {
            CargaByAlumno.forEach { cargaAcademicaItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Materia: ${cargaAcademicaItem.materia}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Créditos: ${cargaAcademicaItem.creditosMateria}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Estado: ${cargaAcademicaItem.estadoMateria}",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

fun getCargaAcademica(
    context: Context,
    navController: NavController,
    viewModel: DataViewModel
){
    val service = RetrofitClient(context).retrofitService2
    val bodyAcademico = cargarRequestBody()
    service.getCargaByAlumno(bodyAcademico).enqueue(object : Callback<Envelope2>{
        override fun onResponse(call: Call<Envelope2>, response: Response<Envelope2>) {
            if (response.isSuccessful) {
                val envelope = response.body()
                val alumnoResultJson: String? =
                    envelope?.body2?.getCargaAcademicaByAlumnoResponse?.getCargaAcademicaByAlumnoResult

                // Deserializa la cadena JSON a AlumnoAcademicoResult
                val json = Json { ignoreUnknownKeys = true }
                val cargaAcademicaItem: List<CargaAcademicaItem>? =
                    alumnoResultJson?.let { json.decodeFromString(it) }

                Log.w("exito", "se obtuvo el perfil 2: ${cargaAcademicaItem}")
                val alumnoAcademicoResultJson = Json.encodeToString(cargaAcademicaItem)
                viewModel.CargaByAlumno = cargaAcademicaItem
                navController.navigate("carga")
            } else {
                showError(
                    context,
                    "Error al obtener el perfil académico. Código de respuesta: ${response.code()}"
                )
            }
        }
        override fun onFailure(call: Call<Envelope2>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud del perfil académico")

        }
    })
}
private fun cargarRequestBody(): RequestBody {
    return """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                <soap:Body>
                <getCargaAcademicaByAlumno xmlns="http://tempuri.org/" />
                  </soap:Body>
                </soap:Envelope>
        """.trimIndent().toRequestBody("text/xml; charset=utf-8".toMediaTypeOrNull())
}
private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}



