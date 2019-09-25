package io.pantheonsite.mtbrutas.model

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("all")
    fun getAllTrails(): Call<JsonArray>
}
