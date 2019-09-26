package io.pantheonsite.mtbrutas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.pantheonsite.mtbrutas.model.ApiAdapter
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var refreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //VIEW
        val trailList: RecyclerView = findViewById(R.id.rvCoupons) //UI
        trailList.layoutManager = LinearLayoutManager(this)
        val trails = ArrayList<Trail>()
        //VIEW

        //CONTROLLER
        refreshLayout = findViewById(R.id.swiperefresh)

        refreshLayout!!.setOnRefreshListener {
            trails.clear()
            loadTrailList(trails, trailList, refreshLayout!!)

            Toast.makeText(
                applicationContext,
                getString(R.string.updated_trail_list),
                Toast.LENGTH_SHORT
            ).show()
        }
        loadTrailList(trails, trailList, refreshLayout!!)
        //CONTROLLER
    }

    private fun loadTrailList(
        trails: ArrayList<Trail>,
        trailList: RecyclerView,
        refreshLayout: SwipeRefreshLayout
    ) {
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
                    trails.add(trail)
                }
                //VIEW
                trailList.adapter = RecyclerTrailAdapter(trails, R.layout.card_trail)
                refreshLayout.isRefreshing = false
                //VIEW
            }
        })
    }
}
