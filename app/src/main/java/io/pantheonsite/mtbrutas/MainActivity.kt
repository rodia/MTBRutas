package io.pantheonsite.mtbrutas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.pantheonsite.mtbrutas.model.ApiAdapter
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //VIEW
        val trailList: RecyclerView = findViewById(R.id.rvCoupons) //UI
        trailList.layoutManager = LinearLayoutManager(this)
        val countries = ArrayList<Trail>()
        //VIEW

        //CONTROLLER
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getAllTrails()

        call.enqueue(object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                val offersJsonArray = response.body()?.asJsonArray
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val trail = Trail(jsonObject)
                    countries.add(trail)
                }
                //VIEW
                trailList.adapter = RecyclerTrailAdapter(countries, R.layout.card_trail)
                //VIEW
            }
        })
        //CONTROLLER
    }
}
