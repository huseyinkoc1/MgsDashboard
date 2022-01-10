package com.mgs.mgsdashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R

class ViewPagerAdapterPetner(private var title: List<String>, private var details: List<String>,private var images: List<Int>): RecyclerView.Adapter<ViewPagerAdapterPetner.Pager2ViewHolder>()  {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  itemTitle : TextView = itemView.findViewById(R.id.textViewN)
        val  itemDetails : TextView = itemView.findViewById(R.id.textViewM)
        val  itemImage : ImageView = itemView.findViewById(R.id.imageViewN)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapterPetner.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.petner_dashboard,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapterPetner.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetails.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return title.size
    }


}