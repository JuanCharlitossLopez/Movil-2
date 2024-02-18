/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.evaluacion_u1.data

import android.content.Context
import android.util.Log
import com.example.evaluacion_u1.network.AddCookiesInterceptor
import com.example.evaluacion_u1.network.LoginSICEApiService
import com.example.evaluacion_u1.network.ReceivedCookiesInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.ResponseBody.Companion.toResponseBody
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitClient(context: Context) {

    private val BASE_URL = "https://sicenet.surguanajuato.tecnm.mx"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AddCookiesInterceptor(context))
        .addInterceptor(ReceivedCookiesInterceptor(context))
        .addInterceptor(createLoggingInterceptor())
        .build()

    private fun createLoggingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val requestHeaders = request.headers
            for (i in 0 until requestHeaders.size) {
                Log.d("HEADER", "${requestHeaders.name(i)}: ${requestHeaders.value(i)}")
            }
            Log.d("Solicitud", "URL: ${request.url}")
            Log.d("Solicitud", "Método: ${request.method}")
            Log.d("Solicitud", "Cuerpo: ${request.body}")
            val response = chain.proceed(request)
            val responseBody = response.body?.string()
            Log.d("Respuesta", "Código: ${response.code}")
            Log.d("Respuesta", "Cuerpo: $responseBody")
            response.newBuilder()
                .body(responseBody?.toResponseBody(response.body?.contentType()))
                .build()
        }
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        .client(client)
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: LoginSICEApiService by lazy {
        retrofit.create(LoginSICEApiService::class.java)
        }
}
