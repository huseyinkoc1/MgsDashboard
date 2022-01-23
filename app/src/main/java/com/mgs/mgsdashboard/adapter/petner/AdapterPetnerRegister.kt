package com.mgs.mgsdashboard.adapter.petner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.petner.Petner
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class AdapterPetnerRegister(private val petnerList: Petner) : RecyclerView.Adapter<AdapterPetnerRegister.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(petnerModel: Petner, position: Int){

            var name = ""
            var surname = ""
            val date = petnerModel.register_users.get(position).created_at ?:""

            if (petnerModel.register_users.get(position).name != null){
                name = "${petnerModel.register_users.get(position).name[0].toUpperCase()+petnerModel.register_users.get(position).name.substring(1)} "?:""
            }

            if (petnerModel.register_users.get(position).surname != null){
                surname = "${petnerModel.register_users.get(position).surname[0].toUpperCase() +petnerModel.register_users.get(position).surname.substring(1)}"
            }


            itemView.textTitle.text = name+" "+surname
            itemView.textTime.text = date
            itemView.imageItem.setImageResource(R.drawable.petner_user)


            if (petnerModel.register_users.count()-1 == position) {
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
        return petnerList.register_users.count()
    }
}