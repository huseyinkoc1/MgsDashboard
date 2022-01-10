package com.mgs.mgsdashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.AvfastKayitModel


class RecyclerAdapterAvFastKayit (val context : Context, val androidVersionList: ArrayList<AvfastKayitModel>) : RecyclerView.Adapter<RecyclerAdapterAvFastKayit.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0?.txtTitle?.text = androidVersionList[p1].codeName
        p0?.txtContent?.text = " ${androidVersionList[p1].versionName}"
        p0?.image.setImageResource(androidVersionList[p1].imgResId!!)
        if (p1 == 9){
            p0?.rcyView.isVisible = false
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.recycler_view_item, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return androidVersionList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.textTitle)
        val txtContent = itemView.findViewById<TextView>(R.id.textTime)
        val image = itemView.findViewById<ImageView>(R.id.imageItem)
        val rcyView = itemView.findViewById<View>(R.id.rcyViewDiveder)
    }
}