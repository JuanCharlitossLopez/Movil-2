package com.example.evaluacion_u1.ui.theme.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.R
import com.example.evaluacion_u1.data.RetrofitClient
import com.example.evaluacion_u1.navigation.AppScreens
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun mostrarDatos(navController: NavController, viewModel: DataViewModel) {
    val alumnoAcademicoResult = viewModel.alumnoAcademicoResult
    val context = LocalContext.current
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Image(
        painter = painterResource(id = R.drawable.fondodate),
        contentDescription = "My background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    if (alumnoAcademicoResult != null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Card(modifier = Modifier
                .padding(30.dp)
                .size(600.dp, 45.dp)){
                Text(text = "Nombre: ${alumnoAcademicoResult.nombre}",modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier
                .padding(30.dp)
                .size(600.dp, 45.dp)){
                Text(text = "Matrícula: ${alumnoAcademicoResult.matricula}",modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier
                .padding(30.dp)
                .size(600.dp, 45.dp)){
                Text(text = "Carrera: ${alumnoAcademicoResult.carrera}",modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier
                .padding(30.dp)
                .size(600.dp, 45.dp)){
                Text(text = "Semestre Actual: ${alumnoAcademicoResult.semActual}",modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally))
            }
            Card(modifier = Modifier
                .padding(30.dp)
                .size(600.dp, 65.dp)){
                Text(text = "Especialidad: ${alumnoAcademicoResult.especialidad}",modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally))
            }
            // Agrega más campos aquí
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { navController.popBackStack() }) {
                Text(text = "Cerrar sesion")
            }
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    //login(context)
                    authenticate(context, user, pass, navController, viewModel)
                    AppScreens.cargaAcademic }) {
                Text(text = "Carga Academica")
            }
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    authenticate2(context, user, pass, navController, viewModel)
                    AppScreens.kardex }) {
                Text(text = "Kardex")
            }
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    authenticate3(context, user, pass, navController, viewModel)
                    AppScreens.CalifByUnit }) {
                Text(text = "Calif Por unidad")
            }
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "No se pudo obtener el perfil académico.")
        }
    }
}

private fun authenticate(
    context: Context,
    matricula: String,
    contrasenia: String,
    navController: NavController,
    viewModel: DataViewModel
) {
    val bodyLogin = loginRequestBody(matricula, contrasenia)
    val service = RetrofitClient(context).retrofitService
    service.login(bodyLogin).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.w("exito", "se obtuvo el perfil")
                //getAcademicProfile(context, navController, viewModel)
                getCargaAcademica(context,navController,viewModel)
            } else {
                showError(
                    context,
                    "Error en la autenticación. Código de respuesta: ${response.code()}"
                )
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud")
        }
    })
}

private fun authenticate2(
    context: Context,
    matricula: String,
    contrasenia: String,
    navController: NavController,
    viewModel: DataViewModel
) {
    val bodyLogin = loginRequestBody(matricula, contrasenia)
    val service = RetrofitClient(context).retrofitService
    service.login(bodyLogin).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.w("exito", "se obtuvo el perfil")
                //getAcademicProfile(context, navController, viewModel)
                //getCargaAcademica(context,navController,viewModel)
                getAllKardex(context,navController,viewModel)
            } else {
                showError(
                    context,
                    "Error en la autenticación. Código de respuesta: ${response.code()}"
                )
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud")
        }
    })
}
private fun authenticate3(
    context: Context,
    matricula: String,
    contrasenia: String,
    navController: NavController,
    viewModel: DataViewModel
) {
    val bodyLogin = loginRequestBody(matricula, contrasenia)
    val service = RetrofitClient(context).retrofitService
    service.login(bodyLogin).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.w("exito", "se obtuvo el perfil")
                //getAcademicProfile(context, navController, viewModel)
                //getCargaAcademica(context,navController,viewModel)
                //getAllKardex(context,navController,viewModel)
                getCalifByUnit(context,navController,viewModel)
            } else {
                showError(
                    context,
                    "Error en la autenticación. Código de respuesta: ${response.code()}"
                )
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud")
        }
    })
}

private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

