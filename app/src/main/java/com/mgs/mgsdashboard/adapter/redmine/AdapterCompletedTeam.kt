package com.mgs.mgsdashboard.adapter.redmine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.redmine.TaskCompletedTeam
import kotlinx.android.synthetic.main.recyler_redmine_first.view.*

class AdapterCompletedTeam(private val pointList: Array<TaskCompletedTeam>) :
    RecyclerView.Adapter<AdapterCompletedTeam.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(pointList: Array<TaskCompletedTeam>, position: Int) {

            val rank = position + 1
            val name = pointList[position].name ?: ""
            val points = pointList[position].points ?: ""

            itemView.textViewRank.text = rank.toString()
            itemView.textViewName.text = name
            itemView.textViewScoreBoard.text = points.toString()
            itemView.imageViewProfile.isVisible = false

            when (position) {
                1 -> itemView.textViewScoreBoard.setBackgroundResource(R.drawable.scoreboardtwo)
                2 -> itemView.textViewScoreBoard.setBackgroundResource(R.drawable.scoreboardthree)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyler_redmine_first, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(pointList, position)
    }

    override fun getItemCount(): Int {
        return 3
    }
}