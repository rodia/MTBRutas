package io.pantheonsite.mtbrutas

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

class Route(trailRoute: String) : Serializable {
    var route: ArrayList<LatLng> = ArrayList()

    init {
        val stringArray = trailRoute.split("|").map { it.trim() }
        stringArray.forEach{ it ->
            val row = it.split(",").map{ it.trim() }
            route.add(LatLng(row[0].toDouble(), row[1].toDouble()))
        }
    }
}
