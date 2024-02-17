package com.example.evaluacion_u1.network

import android.view.PixelCopy.Request
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL = "http://sicenet.surguanajuato.tecnm.mx"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface SiceApiService{

    @POST("/ws/wsalumnos.asmx")
    suspend fun acceso(): RequestBody
    @GET()
    suspend fun conexion(): RequestBody
}

object SiceApi{
    val retrofitService : SiceApiService by lazy{
        retrofit.create(SiceApiService::class.java)
    }
}





