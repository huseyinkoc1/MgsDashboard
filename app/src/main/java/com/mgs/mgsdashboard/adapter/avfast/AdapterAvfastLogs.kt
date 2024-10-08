package com.mgs.mgsdashboard.adapter.avfast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.avfast.Avfast
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class AdapterAvfastLogs(private val avFastList: Avfast) :
    RecyclerView.Adapter<AdapterAvfastLogs.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(avfastModel: Avfast, position: Int) {

            var description = avfastModel.logs.get(position).description ?:""
            var date = avfastModel.logs.get(position).created_at ?:""

            itemView.textTitle.text = description
            itemView.textTime.text = date
            itemView.imageItem.setImageResource(R.drawable.announce)

            if (avfastModel.logs.count() - 1 == position) {
                itemView.rcyViewDiveder.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(avFastList, position)
    }

    override fun getItemCount(): Int {
        return avFastList.logs.count()
    }
}