package com.mgs.mgsdashboard.adapter.avfast

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
import com.mgs.mgsdashboard.model.avfast.Avfast

class ViewPagerAdapterAvfast(private val avfast: Avfast) : RecyclerView.Adapter<ViewPagerAdapterAvfast.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  itemTitle : TextView = itemView.findViewById(R.id.textViewA)
        val  itemDetails : TextView = itemView.findViewById(R.id.textViewB)
        val chart : BarChart = itemView.findViewById(R.id.barChartTaskCompletion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.avfast_dashboard,parent,false))
    }

    override fun getItemCount(): Int {
        return 6
    }


    fun configureBarchart(holder: Pager2ViewHolder, barEntry: ArrayList<BarEntry>){



        //holder.chart.axisLeft.setDrawGridLines(true)
        //holder.chart.xAxis.setDrawGridLines(true)
        //holder.chart.xAxis.setDrawAxisLine(true)
        holder.chart.axisRight.isEnabled = false
        holder.chart.legend.isEnabled = true
        holder.chart.legend.form = Legend.LegendForm.NONE
        holder.chart.description.isEnabled = false
        //holder.chart.setPinchZoom(true)
        //holder.chart.setTouchEnabled(false)



        holder.chart.xAxis.textColor = Color.parseColor("#FFFFFF")
        holder.chart.xAxis.textSize = 10f
        holder.chart.xAxis.axisLineColor = Color.parseColor("#FFFFFF")
        holder.chart.xAxis.spaceMin = 0.01f
        holder.chart.xAxis.granularity = 1f
        holder.chart.xAxis.axisLineWidth = 1.1f
        holder.chart.xAxis.typeface = Typeface.MONOSPACE
        //holder.chart.xAxis.spaceMax = 0.1f
        holder.chart.xAxis.setCenterAxisLabels(false)


        holder.chart.axisLeft.textColor = Color.parseColor("#FFFFFF")
        holder.chart.axisLeft.textSize = 10f
        holder.chart.axisLeft.axisLineColor = Color.parseColor("#FFFFFF")
        holder.chart.axisLeft.axisLineWidth = 1f
        holder.chart.axisLeft.setStartAtZero(true)
        holder.chart.axisLeft.setCenterAxisLabels(false)
        //holder.chart.axisLeft.granularity = 10f
        holder.chart.axisLeft.typeface = Typeface.MONOSPACE


        val barDataSet = BarDataSet(barEntry,"")
        barDataSet.valueTextColor = Color.parseColor("#C3C3C3")
        barDataSet.setColors(Color.parseColor("#EEA320"))
        barDataSet.isHighlightEnabled = false
        val barData = BarData(barDataSet)
        holder.chart.data = barData

        //val barData = BarData(barDataSet)
        holder.chart.animateY(1500)
        holder.chart.animateX(1500)
        holder.chart.setFitBars(true)
        //holder.chart.data = barData
        //holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        holder.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        holder.chart.invalidate()
    }


    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {

        val barEntry = arrayListOf<BarEntry>()
        val xValueList = arrayListOf<String>()


        if (position == 0){

            val size = avfast.monthly_total_users_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),avfast.monthly_total_users_chart[x].count.toFloat()))
                xValueList.add("${avfast.monthly_total_users_chart[x].created_at.subSequence(5,10)}")
            }
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)

            holder.itemTitle.text = "BU AY KAYITLI KULLANICI"
            holder.itemDetails.text = avfast.monthly_total_users_count.toString()

        } else if (position == 1){

            val size = avfast.daily_logged_in_users_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),avfast.daily_logged_in_users_chart[x].count.toFloat()))
                xValueList.add("${avfast.daily_logged_in_users_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "GÜNLÜK GİRİŞ"
            holder.itemDetails.text = avfast.daily_logged_in_users_count.toString()

        } else if (position == 2){

            val size = avfast.weekly_tasks_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),avfast.weekly_tasks_chart[x].count.toFloat()))
                xValueList.add("${avfast.weekly_tasks_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "YENİ TASK"
            holder.itemDetails.text = avfast.weekly_tasks_count.toString()

        } else if (position == 3) {

            val size = avfast.weekly_applied_tasks_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),avfast.weekly_applied_tasks_chart[x].count.toFloat()))
                xValueList.add("${avfast.weekly_applied_tasks_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "BAŞVURMA"
            holder.itemDetails.text = avfast.weekly_applied_tasks_count.toString()

        } else if (position == 4){

            val size = avfast.weekly_evaluated_tasks_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),avfast.weekly_evaluated_tasks_chart[x].count.toFloat()))
                xValueList.add("${avfast.weekly_evaluated_tasks_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "TAMAMLANAN"
            holder.itemDetails.text = avfast.weekly_done_tasks_count.toString()

        } else {

            val size = avfast.weekly_done_tasks_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),avfast.weekly_done_tasks_chart[x].count.toFloat()))
                xValueList.add("${avfast.weekly_done_tasks_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "DEĞERLENDİRME"
            holder.itemDetails.text = avfast.weekly_evaluated_tasks_count.toString()
        }

        configureBarchart(holder,barEntry)


    }
}