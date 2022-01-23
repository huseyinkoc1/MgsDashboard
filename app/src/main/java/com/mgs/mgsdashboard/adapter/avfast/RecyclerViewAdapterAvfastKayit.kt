package com.mgs.mgsdashboard.adapter.avfast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.avfast.Avfast
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerViewAdapterAvfastKayit(private val avFastList: Avfast) : RecyclerView.Adapter<RecyclerViewAdapterAvfastKayit.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(avfastModel: Avfast, position: Int){

            var name = ""
            var surname = ""
            var date = avfastModel.register_users.get(position).created_at ?:""

            if (avfastModel.register_users.get(position).name != null){
                name = "${avfastModel.register_users.get(position).name[0].toUpperCase() + avfastModel.register_users.get(position).name.substring(1)}" ?:""
            }

            if (avfastModel.register_users.get(position).surname != null){
                surname = "${avfastModel.register_users.get(position).surname[0].toUpperCase() + avfastModel.register_users.get(position).surname.substring(1)}" ?:""
            }




            itemView.textTitle.text = name+" "+surname
            itemView.textTime.text = date
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