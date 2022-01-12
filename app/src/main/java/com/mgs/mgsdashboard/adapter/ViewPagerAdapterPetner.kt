package com.mgs.mgsdashboard.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.model.avfastApi.Avfast
import com.mgs.mgsdashboard.model.petnerApi.Petner

class ViewPagerAdapterPetner(private var title: List<String>, private var details: List<Int>, private val petner: Petner): RecyclerView.Adapter<ViewPagerAdapterPetner.Pager2ViewHolder>()  {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  itemTitle : TextView = itemView.findViewById(R.id.textViewN)
        val  itemDetails : TextView = itemView.findViewById(R.id.textViewM)
        val  chart : BarChart = itemView.findViewById(R.id.barChartPetner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapterPetner.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.petner_dashboard,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapterPetner.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetails.text = details[position].toString()

        if (position == 0){

            val barEntry0 = arrayListOf<BarEntry>()
            val xValueList = arrayListOf<String>()
            val size = petner.users_in_last_five_months_chart.size
            for (x in 0..size-1){
                barEntry0.add(BarEntry(x.toFloat(),petner.users_in_last_five_months_chart[x].count.toFloat()))
                xValueList.add("${petner.users_in_last_five_months_chart[x].created_at.subSequence(5,10)}")
            }
            val barDataSet = BarDataSet(barEntry0,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#691296"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            //holder.chart.xAxis.labelRotationAngle = 90f

        }else if (position == 1){

            val barEntry1 = arrayListOf<BarEntry>()
            val size = petner.users_logged_ln_last_week_chart.size
            for (x in 0..size-1){
                barEntry1.add(BarEntry(x.toFloat(),petner.users_logged_ln_last_week_chart.size.toFloat()))
            }
            val barDataSet = BarDataSet(barEntry1,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#691296"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData

        }else if (position == 2){
            val barEntry2 = arrayListOf<BarEntry>()
            val xValueList = arrayListOf<String>()
            val size = petner.posts_in_last_five_months_chart.size
            for (x in 0..size-1){
                barEntry2.add(BarEntry(x.toFloat(),petner.posts_in_last_five_months_chart[x].count.toFloat()))
                xValueList.add("${petner.posts_in_last_five_months_chart[x].created_at.subSequence(5,10)}")
            }

            val barDataSet = BarDataSet(barEntry2,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#691296"))
            val barData = BarData(barDataSet)
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.chart.data = barData


        }else if (position == 3) {
            val barEntry3 = arrayListOf<BarEntry>()
            val xValueList = arrayListOf<String>()
            val size = petner.conversations_in_last_five_weeks_chart.size
            for (x in 0..size-1){
                barEntry3.add(BarEntry(x.toFloat(),petner.conversations_in_last_five_weeks_chart[x].count.toFloat()))
                xValueList.add("${petner.conversations_in_last_five_weeks_chart[x].created_at.subSequence(5,10)}")
            }

            val barDataSet = BarDataSet(barEntry3,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#691296"))
            val barData = BarData(barDataSet)
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.chart.data = barData
        }
        else{
            val barEntry4 = arrayListOf<BarEntry>()
            val xValueList = arrayListOf<String>()
            val size = petner.comments_in_last_five_months_chart.size
            for (x in 0..size-1){
                barEntry4.add(BarEntry(x.toFloat(),petner.comments_in_last_five_months_chart[x].count.toFloat()))
                xValueList.add("${petner.comments_in_last_five_months_chart[x].created_at.subSequence(5,10)}")
            }

            val barDataSet = BarDataSet(barEntry4,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#691296"))
            val barData = BarData(barDataSet)
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.chart.data = barData
        }


        holder.chart.axisRight.isEnabled = false
        holder.chart.legend.isEnabled = true
        holder.chart.legend.form = Legend.LegendForm.NONE
        holder.chart.description.isEnabled = false
        //holder.chart.setPinchZoom(true)
        holder.chart.setTouchEnabled(false)


        holder.chart.xAxis.textColor = Color.parseColor("#404042")
        holder.chart.xAxis.textSize = 14f
        holder.chart.xAxis.axisLineColor = Color.parseColor("#404042")
        holder.chart.xAxis.spaceMin = 0.01f
        holder.chart.xAxis.granularity = 1f
        holder.chart.xAxis.axisLineWidth = 1.1f
        holder.chart.xAxis.typeface = Typeface.MONOSPACE
        //holder.chart.xAxis.spaceMax = 0.1f
        holder.chart.xAxis.setCenterAxisLabels(false)


        holder.chart.axisLeft.textColor = Color.parseColor("#404042")
        holder.chart.axisLeft.textSize = 14f
        holder.chart.axisLeft.axisLineColor = Color.parseColor("#404042")
        holder.chart.axisLeft.axisLineWidth = 1f
        holder.chart.axisLeft.setStartAtZero(true)
        holder.chart.axisLeft.setCenterAxisLabels(false)
        //holder.chart.axisLeft.granularity = 10f
        holder.chart.axisLeft.typeface = Typeface.MONOSPACE




        //val barData = BarData(barDataSet)
        holder.chart.animateY(1500)
        holder.chart.animateX(1500)
        holder.chart.setFitBars(true)
        //holder.chart.data = barData
        //holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        holder.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        holder.chart.invalidate()

    }

    override fun getItemCount(): Int {
        return title.size
    }


}