package com.mgs.mgsdashboard.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ViewPagerAdapterAvfast(private var title: List<String>, private var details: List<Int>, private val avfast: Avfast) : RecyclerView.Adapter<ViewPagerAdapterAvfast.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  itemTitle : TextView = itemView.findViewById(R.id.textViewA)
        val  itemDetails : TextView = itemView.findViewById(R.id.textViewB)
        //val  itemImage : ImageView = itemView.findViewById(R.id.imageView2)
        val chart : BarChart = itemView.findViewById(R.id.barChart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapterAvfast.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.avfast_dashboard,parent,false))
    }

    override fun getItemCount(): Int {
        return title.size
    }

    private fun configureBarchart(){

    }

    override fun onBindViewHolder(holder: ViewPagerAdapterAvfast.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetails.text = details[position].toString()
        //holder.itemImage.setImageResource(R.drawable.dash)

        //val barEntry = arrayListOf<BarEntry>()

        //holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter()


        if (position == 0){

            val barEntry0 = arrayListOf<BarEntry>()
            val xValueList = arrayListOf<String>()
            val size = avfast.monthly_total_users_chart.size
            for (x in 0..size-1){
                //println("*** Created At: ${avfast.monthly_total_users_chart[x].created_at} Month: ${avfast.monthly_total_users_chart[x].month} Count: ${avfast.monthly_total_users_chart[x].count} ")
                barEntry0.add(BarEntry(x.toFloat(),avfast.monthly_total_users_chart[x].count.toFloat()))
                xValueList.add("${avfast.monthly_total_users_chart[x].created_at.subSequence(5,10)}")
            }
            val barDataSet = BarDataSet(barEntry0,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#EEA320"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            //holder.chart.xAxis.labelRotationAngle = 90f

        }else if (position == 1){

            val barEntry1 = arrayListOf<BarEntry>()
            val xValueList = arrayListOf<String>()
            val size = avfast.daily_logged_in_users_chart.size
            for (x in 0..size-1){
                barEntry1.add(BarEntry(x.toFloat(),avfast.daily_logged_in_users_chart[x].count.toFloat()))
                xValueList.add("${avfast.daily_logged_in_users_chart[x].created_at.subSequence(5,10)}")
            }
            val barDataSet = BarDataSet(barEntry1,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#EEA320"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)

        }else if (position == 2){
            val barEntry2 = arrayListOf<BarEntry>()

            val size = avfast.weekly_tasks_chart.size
            for (x in 0..size-1){
                barEntry2.add(BarEntry(x.toFloat(),avfast.weekly_applied_tasks_chart.size.toFloat()))
            }

            val barDataSet = BarDataSet(barEntry2,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#EEA320"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
            

        }else if (position == 3) {
            val barEntry3 = arrayListOf<BarEntry>()
            val size = avfast.weekly_applied_tasks_chart.size
            for (x in 0..size-1){
                barEntry3.add(BarEntry(x.toFloat(),avfast.weekly_applied_tasks_chart.size.toFloat()))
            }

            val barDataSet = BarDataSet(barEntry3,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#EEA320"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
        }
        else if (position == 4){
            val barEntry4 = arrayListOf<BarEntry>()
            val size = avfast.weekly_evaluated_tasks_chart.size
            for (x in 0..size-1){
                barEntry4.add(BarEntry(x.toFloat(),avfast.weekly_evaluated_tasks_chart.size.toFloat()))
            }

            val barDataSet = BarDataSet(barEntry4,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#EEA320"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
        }
        else{
            val barEntry5 = arrayListOf<BarEntry>()
            val size = avfast.weekly_done_tasks_chart.size
            for (x in 0..size-1){
                barEntry5.add(BarEntry(x.toFloat(),avfast.weekly_done_tasks_chart.size.toFloat()))
            }

            val barDataSet = BarDataSet(barEntry5,"")
            barDataSet.valueTextColor = Color.parseColor("#727272")
            barDataSet.setColors(Color.parseColor("#EEA320"))
            val barData = BarData(barDataSet)
            holder.chart.data = barData
        }






        //holder.chart.axisLeft.setDrawGridLines(true)
        //holder.chart.xAxis.setDrawGridLines(true)
        //holder.chart.xAxis.setDrawAxisLine(true)
        holder.chart.axisRight.isEnabled = false
        holder.chart.legend.isEnabled = true
        holder.chart.legend.form = Legend.LegendForm.NONE
        holder.chart.description.isEnabled = false
        //holder.chart.setPinchZoom(true)
        holder.chart.setTouchEnabled(false)


        holder.chart.xAxis.textColor = Color.parseColor("#FFFFFF")
        holder.chart.xAxis.textSize = 14f
        holder.chart.xAxis.axisLineColor = Color.parseColor("#FFFFFF")
        holder.chart.xAxis.spaceMin = 0.01f
        holder.chart.xAxis.granularity = 1f
        holder.chart.xAxis.axisLineWidth = 1.1f
        holder.chart.xAxis.typeface = Typeface.MONOSPACE
        //holder.chart.xAxis.spaceMax = 0.1f
        holder.chart.xAxis.setCenterAxisLabels(false)


        holder.chart.axisLeft.textColor = Color.parseColor("#FFFFFF")
        holder.chart.axisLeft.textSize = 14f
        holder.chart.axisLeft.axisLineColor = Color.parseColor("#FFFFFF")
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




}