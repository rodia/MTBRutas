package io.pantheonsite.mtbrutas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnPolylineClickListener {

    private lateinit var mMap: GoogleMap
    private var trail: Trail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        trail = intent.extras!!.get("TRAIL") as Trail
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val route = Route(trail!!.route)
        val mappedRoute = PolylineOptions()

        route.route.forEach{
            mappedRoute.add(it)
        }
        val firstElement = route.route.first()

        val polyline = googleMap.addPolyline(mappedRoute)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstElement, 19.0f))

        googleMap.setOnPolylineClickListener(this)
    }

    override fun onPolylineClick(p0: Polyline?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
