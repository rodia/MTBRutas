package io.pantheonsite.mtbrutas

import com.google.gson.JsonObject
import java.io.Serializable

class Trail(trailJson: JsonObject?) : Serializable {

    lateinit var name: String
    lateinit var distance: String
    lateinit var route: String
    lateinit var region: String
    lateinit var code: String
    lateinit var image: String

    init {
        try {
            name = trailJson!!.get(NAME).asString
            distance = trailJson.get(DISTANCE).asString
            region = trailJson.get(REGION).asString
            code = trailJson.get(CODE).asString
            route = trailJson.get(TRAIL_ROUTE).asString
            image = trailJson.get(IMAGE_ROUTE).asString
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object {
        private val NAME        = "title"
        private val DISTANCE    = "field_distance"
        private val TRAIL_ROUTE = "field_description_route"
        private val IMAGE_ROUTE = "field_image_route"
        private val REGION      = "field_region"
        private val CODE        = "nid"
    }
}
