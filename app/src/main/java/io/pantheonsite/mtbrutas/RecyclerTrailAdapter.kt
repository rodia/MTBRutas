package io.pantheonsite.mtbrutas

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RecyclerTrailAdapter(var countries : ArrayList<Trail>, var resource: Int) : RecyclerView.Adapter<RecyclerTrailAdapter.CardCountryHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCountryHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(resource, p0, false)
        return CardCountryHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(p0: CardCountryHolder, p1: Int) {
        val country = countries.get(p1)
        p0.setDataCard(country)
    }

    class CardCountryHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var trail: Trail? = null
        private var img_view_trail: ImageView = v.findViewById(R.id.imageTrail)
        private var text_view_name: TextView = v.findViewById(R.id.nameTrail)
        private var text_view_distance: TextView = v.findViewById(R.id.distanceTrail)

        init {
            v.setOnClickListener(this)
        }

        fun setDataCard(trail: Trail){
            this.trail = trail
            Picasso.get().load(trail.image).resize(520, 520).centerCrop().into(img_view_trail)
            text_view_name.setText(trail.name)
            text_view_distance.setText(trail.distance)
        }

        override fun onClick(v: View) {
            val context = v.context
            val showPhotoIntent = Intent(context, MapsActivity::class.java)
            showPhotoIntent.putExtra("TRAIL", trail)
            context.startActivity(showPhotoIntent)
        }
    }
}
