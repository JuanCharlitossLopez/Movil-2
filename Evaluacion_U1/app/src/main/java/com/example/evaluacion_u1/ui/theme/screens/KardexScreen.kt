package com.example.evaluacion_u1.ui.theme.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.data.RetrofitClient
import com.example.evaluacion_u1.model.Envelope3
import com.example.evaluacion_u1.model.Kardex
import com.example.evaluacion_u1.model.Promedio
import com.example.evaluacion_u1.navigation.AppScreens
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun mostrarKardex(navController: NavController, viewModel: DataViewModel) {
    val kardexAcademicoResult = viewModel.kardexByAlumno

    if (kardexAcademicoResult != null) {
        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(vertical = 1.dp)
                .size(1050.dp, 200.dp)
        ) {
            Text(
                text = "Promedio general : ${kardexAcademicoResult.promedio}",
            )
        }
        Spacer(modifier = Modifier.height(500.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(kardexAcademicoResult.lstKardex) { kardex ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 1.dp)
                            .size(1050.dp, 200.dp)
                    ) {
                        Text(
                            text = "Materia: ${kardex.materia}",
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Calif: ${kardex.calif}",
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Cdtos: ${kardex.cdts}",
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Clave: ${kardex.clvMat}",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

            }
        }
    }
}


fun getAllKardex(
    context: Context,
    navController: NavController,
    viewModel: DataViewModel
) {
    val service = RetrofitClient(context).retrofitService3
    val bodyAcademico = KardexRequestBody()
    service.getAllkardex(bodyAcademico).enqueue(object : Callback<Envelope3> {
        override fun onResponse(call: Call<Envelope3>, response: Response<Envelope3>) {
            if (response.isSuccessful) {
                val envelope = response.body()
                val alumnoResultJson: String? =
                    envelope?.body3?.getAllKardexConPromedioByAlumnoResponse?.getAllKardexConPromedioByAlumnoResult

                // Deserializa la cadena JSON a AlumnoAcademicoResult
                val json = Json { ignoreUnknownKeys = true }
                val kardexAcademicaItem: Kardex? =
                    alumnoResultJson?.let { json.decodeFromString(it) }

                Log.w("exito", "se obtuvo el perfil 2: ${kardexAcademicaItem}")
                val alumnoAcademicoResultJson = Json.encodeToString(kardexAcademicaItem)
                viewModel.kardexByAlumno = kardexAcademicaItem

                navController.navigate("kardex")
            } else {
                showError(
                    context,
                    "Error al obtener el perfil académico. Código de respuesta: ${response.code()}"
                )
            }
        }

        override fun onFailure(call: Call<Envelope3>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud del perfil académico")

        }
    })
}

private fun KardexRequestBody(): RequestBody {
    return """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getAllKardexConPromedioByAlumno xmlns="http://tempuri.org/">
      <aluLineamiento>3</aluLineamiento>
    </getAllKardexConPromedioByAlumno>
  </soap:Body>
</soap:Envelope>

        """.trimIndent().toRequestBody("text/xml; charset=utf-8".toMediaTypeOrNull())
}

private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}