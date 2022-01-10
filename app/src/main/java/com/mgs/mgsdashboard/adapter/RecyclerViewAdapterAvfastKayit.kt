package com.mgs.mgsdashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.avfastApi.Avfast
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerViewAdapterAvfastKayit(private val avFastList: Avfast) : RecyclerView.Adapter<RecyclerViewAdapterAvfastKayit.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(avfastModel: Avfast, position: Int){
            itemView.textTitle.text = avfastModel.register_users.get(position).name+" "+avfastModel.register_users.get(position).surname
            itemView.textTime.text = avfastModel.register_users.get(position).created_at
            itemView.imageItem.setImageResource(R.drawable.avfastprofile)
            if (avfastModel.register_users.count()-1 == position){
                itemView.rcyViewDiveder.isVisible = false
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(avFastList,position)
    }

    override fun getItemCount(): Int {
        return avFastList.register_users.count()
    }
}