package com.mysunnyweather.android.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mysunnyweather.android.MySunnyWeatherApplication
import com.mysunnyweather.android.R
import com.mysunnyweather.android.logic.model.Place

class PlaceAdapter(private val fragment: Fragment,private val placeList: List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeName = itemView.findViewById<TextView>(R.id.placeName)
        val placeAddress = itemView.findViewById<TextView>(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        val viewHolder = ViewHolder(itemView)
        return viewHolder
    }

    override fun getItemCount() = placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

}