package com.example.evaluacion_u1.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.example.evaluacion_u1.model.Envelope
import com.example.evaluacion_u1.model.Envelope2
import com.example.evaluacion_u1.model.Envelope3
import com.example.evaluacion_u1.model.Envelope4

interface LoginSICEApiService {
    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/accesoLogin"
    )
    @POST("/ws/wsalumnos.asmx")
    fun login(@Body body: RequestBody): Call<ResponseBody>

    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/getAlumnoAcademicoWithLineamiento"
    )
    @POST("/ws/wsalumnos.asmx")
    fun getAcademicProfile(@Body body: RequestBody): Call<Envelope>
}

//CArga Academica
interface CargaAcademicaApiService {
    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/accesoLogin"
    )
    @POST("/ws/wsalumnos.asmx")
    fun login2(@Body body2: RequestBody): Call<ResponseBody>

    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/getCargaAcademicaByAlumno"
    )
    @POST("/ws/wsalumnos.asmx")
    fun getCargaByAlumno(@Body body2: RequestBody): Call<Envelope2>
}

interface KardexApiService{
    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/accesoLogin"
    )
    @POST("/ws/wsalumnos.asmx")
    fun login3(@Body body3: RequestBody): Call<ResponseBody>

    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/getAllKardexConPromedioByAlumno"
    )
    @POST("/ws/wsalumnos.asmx")
    fun getAllkardex(@Body body3: RequestBody): Call<Envelope3>
}

interface CalifByUnidades{
    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/accesoLogin"
    )
    @POST("/ws/wsalumnos.asmx")
    fun login3(@Body body3: RequestBody): Call<ResponseBody>

    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/getCalifUnidadesByAlumno"
    )
    @POST("/ws/wsalumnos.asmx")
    fun getAllCalifbyUnidad(@Body body4: RequestBody): Call<Envelope4>
}