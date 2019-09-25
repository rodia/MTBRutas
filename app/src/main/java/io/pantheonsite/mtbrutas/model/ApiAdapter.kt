package io.pantheonsite.mtbrutas.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAdapter {
    val urlApi = "http://dev-mtb-rutas.pantheonsite.io/api/v1/"

    fun getClientService(): ApiService {
        val authInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()

            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return  retrofit.create(ApiService::class.java)
    }
}
