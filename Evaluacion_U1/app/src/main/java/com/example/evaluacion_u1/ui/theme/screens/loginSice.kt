package com.example.evaluacion_u1.ui.theme.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evaluacion_u1.R
import com.example.evaluacion_u1.data.RetrofitClient
import com.example.evaluacion_u1.model.AlumnoAcademicoResult
import com.example.evaluacion_u1.model.Envelope
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody

@Composable
fun PantallaPrincipal(navController: NavController, viewModel: DataViewModel) {
    val context = LocalContext.current
    var user by remember { mutableStateOf("") }
    var pass by remember {
        mutableStateOf("")
    }
    var isValidUser by remember {
        mutableStateOf(false)
    }
    var isValidPass by remember {
        mutableStateOf(false)
    }
    var passVisible by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondodate),
            contentDescription = "My background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
            )
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Card(
                Modifier.padding(12.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Usuario(
                        user = user,
                        userChange = {
                            user = it
                            isValidUser = it.length >= 0 //9
                        },
                        isValidUser = isValidUser
                    )
                    Password(
                        pass = pass,
                        passChange = {
                            pass = it
                            isValidPass = pass.length >= 0 //6
                        },
                        passVisible = passVisible,
                        passVisibleChange = {
                            passVisible = !passVisible
                        },
                        isValidPass = isValidPass
                    )
                    IniciarSesion(
                        context = context,
                        isValidUser = isValidUser,
                        isValidPass = isValidPass,
                        nControl = user,
                        password = pass,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
    pass: String,
    passChange: (String) -> Unit,
    passVisible: Boolean,
    passVisibleChange: () -> Unit,
    isValidPass: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = pass,
            onValueChange = passChange,
            label = {Text(text="Password")},
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passVisible) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                }
                IconButton(onClick = { passVisibleChange() }) {
                    Icon(imageVector = image, contentDescription = "Ver contraseña")
                }
            },
            visualTransformation = if (passVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = if (isValidPass) Color.Green else Color.Red,
                unfocusedIndicatorColor = if (isValidPass) Color.Green else Color.Red
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Usuario(
    user: String,
    userChange: (String) -> Unit,
    isValidUser: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = user, onValueChange = userChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = {Text(text="Matricula")},
            maxLines = 1,
            singleLine = true,
            colors = if (isValidUser) {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Green,
                    focusedBorderColor = Color.Green
                )
            } else {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Red,
                    focusedBorderColor = Color.Red
                )
            }
        )
    }
}

@Composable
fun IniciarSesion(
    context: Context,
    isValidUser: Boolean,
    isValidPass: Boolean,
    nControl: String,
    password: String,
    navController: NavController,
    viewModel: DataViewModel
) {
    Row(
        Modifier
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                login(context)
                authenticate(context, nControl, password, navController, viewModel)
            },
            enabled = isValidUser && isValidPass
        ){
            Text(text="Iniciar Sesion")
        }
    }
}

fun login(context: Context) {
    Toast.makeText(context, "Iniciando sesión", Toast.LENGTH_SHORT).show()
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
                getAcademicProfile(context, navController, viewModel)
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

private fun getAcademicProfile(
    context: Context,
    navController: NavController,
    viewModel: DataViewModel
) {
    val service = RetrofitClient(context).retrofitService
    val bodyProfile = profileRequestBody()
    service.getAcademicProfile(bodyProfile).enqueue(object : Callback<Envelope> {
        override fun onResponse(call: Call<Envelope>, response: Response<Envelope>) {
            if (response.isSuccessful) {
                val envelope = response.body()
                val alumnoResultJson: String? =
                    envelope?.body?.getAlumnoAcademicoWithLineamientoResponse?.getAlumnoAcademicoWithLineamientoResult

                // Deserializa la cadena JSON a AlumnoAcademicoResult
                val json = Json { ignoreUnknownKeys = true }
                val alumnoAcademicoResult: AlumnoAcademicoResult? =
                    alumnoResultJson?.let { json.decodeFromString(it) }

                Log.w("exito", "se obtuvo el perfil 2: ${alumnoAcademicoResult}")
                val alumnoAcademicoResultJson = Json.encodeToString(alumnoAcademicoResult)
                viewModel.alumnoAcademicoResult = alumnoAcademicoResult
                navController.navigate("data")
            } else {
                showError(
                    context,
                    "Error al obtener el perfil académico. Código de respuesta: ${response.code()}"
                )
            }
        }

        override fun onFailure(call: Call<Envelope>, t: Throwable) {
            t.printStackTrace()
            showError(context, "Error en la solicitud del perfil académico")
        }
    })
}


private fun loginRequestBody(matricula: String, contrasenia: String): RequestBody {
    return """
        <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
          <soap:Body>
            <accesoLogin xmlns="http://tempuri.org/">
              <strMatricula>$matricula</strMatricula>
              <strContrasenia>$contrasenia</strContrasenia>
              <tipoUsuario>ALUMNO</tipoUsuario>
            </accesoLogin>
          </soap:Body>
        </soap:Envelope>
    """.trimIndent().toRequestBody("text/xml; charset=utf-8".toMediaTypeOrNull())
}

public fun profileRequestBody(): RequestBody {
    return """
        <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
          <soap:Body>
            <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
          </soap:Body>
        </soap:Envelope>
    """.trimIndent().toRequestBody("text/xml; charset=utf-8".toMediaTypeOrNull())
}

private fun showError(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}