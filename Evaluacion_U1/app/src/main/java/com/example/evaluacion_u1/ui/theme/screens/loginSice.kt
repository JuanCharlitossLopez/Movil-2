package com.example.evaluacion_u1.ui.theme.screens

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Usuario(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle, contentDescription = "Imagen de usuario",
            modifier = Modifier.size(80.dp)
        )
        var user by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = user, onValueChange = { newText ->
                user = newText
            },
            label = { Text(text = "Ingresa tu Matricula") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVer by remember { mutableStateOf(false) }
        val icon = if(passwordVer){
            Icons.Default.Favorite}else{
            Icons.Default.FavoriteBorder}
        OutlinedTextField(value = password, onValueChange = { password = it },
            label = { Text(text = "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordVer = !passwordVer }) {
                    Icon(imageVector = icon, contentDescription = "Ver password")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if(passwordVer){
                VisualTransformation.None}else{
                PasswordVisualTransformation()
            }
        )
       
    }
}


