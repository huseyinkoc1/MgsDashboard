package com.mgs.mgsdashboard.adapter.redmine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.redmine.TaskCompletedTeam
import kotlinx.android.synthetic.main.recyler_redmine_second.view.*

class AdapterCompletedTeamSecond (private val pointList:Array<TaskCompletedTeam>) : RecyclerView.Adapter<AdapterCompletedTeamSecond.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(pointList:Array<TaskCompletedTeam>, position: Int){


            val rank = position+4
            val name = pointList[position+3].name ?:""
            val points =  pointList[position+3].points ?:""

            itemView.textViewRank.text = rank.toString()
            itemView.textViewName.text = name
            itemView.textViewStoryPoint.text = points.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_redmine_second,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(pointList,position)
    }

    override fun getItemCount(): Int {
        return pointList.size-3
    }
}