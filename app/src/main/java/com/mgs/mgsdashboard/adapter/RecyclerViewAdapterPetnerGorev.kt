package com.mgs.mgsdashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.petnerApi.Petner
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerViewAdapterPetnerGorev(private val petnerList: Petner) : RecyclerView.Adapter<RecyclerViewAdapterPetnerGorev.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(petnerModel: Petner, position: Int){
            itemView.textTitle.text = petnerModel.logs[position].description
            itemView.textTime.text = petnerModel.logs[position].created_at
            itemView.imageItem.setImageResource(R.drawable.petnerannounce)
            if (petnerModel.logs.count()-1 == position){
                itemView.rcyViewDiveder.isVisible = false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(petnerList,position)
    }

    override fun getItemCount(): Int {
        return petnerList.logs.count()
    }
}