package com.mgs.mgsdashboard.adapter.petner

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
import com.mgs.mgsdashboard.model.petner.Petner

class AdapterPetnerChart(private val petner: Petner): RecyclerView.Adapter<AdapterPetnerChart.Pager2ViewHolder>()  {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  itemTitle : TextView = itemView.findViewById(R.id.textViewN)
        val  itemDetails : TextView = itemView.findViewById(R.id.textViewM)
        val  chart : BarChart = itemView.findViewById(R.id.barChartPetner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.petner_dashboard,parent,false))
    }

    fun configureBarchart(holder: Pager2ViewHolder, barEntry: ArrayList<BarEntry>){

        holder.chart.axisRight.isEnabled = false
        holder.chart.legend.isEnabled = true
        holder.chart.legend.form = Legend.LegendForm.NONE
        holder.chart.description.isEnabled = false
        //holder.chart.setPinchZoom(true)
        //holder.chart.setTouchEnabled(false)


        holder.chart.xAxis.textColor = Color.parseColor("#404042")
        holder.chart.xAxis.textSize = 10f
        holder.chart.xAxis.axisLineColor = Color.parseColor("#404042")
        holder.chart.xAxis.spaceMin = 0.01f
        holder.chart.xAxis.granularity = 1f
        holder.chart.xAxis.axisLineWidth = 1.1f
        holder.chart.xAxis.typeface = Typeface.MONOSPACE
        //holder.chart.xAxis.spaceMax = 0.1f
        holder.chart.xAxis.setCenterAxisLabels(false)


        holder.chart.axisLeft.textColor = Color.parseColor("#404042")
        holder.chart.axisLeft.textSize = 10f
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

        val barDataSet = BarDataSet(barEntry,"")
        barDataSet.valueTextColor = Color.parseColor("#727272")
        barDataSet.setColors(Color.parseColor("#691296"))
        barDataSet.isHighlightEnabled = false
        val barData = BarData(barDataSet)
        holder.chart.data = barData

        //holder.chart.data = barData
        //holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        holder.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        holder.chart.invalidate()
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {

        val barEntry = arrayListOf<BarEntry>()
        val xValueList = arrayListOf<String>()

        if (position == 0){

            val size = petner.users_in_last_five_months_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),petner.users_in_last_five_months_chart[x].count.toFloat()))
                xValueList.add("${petner.users_in_last_five_months_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "BU AY KAYITLI KULLANICI"
            holder.itemDetails.text = petner.users_count.toString()


        }else if (position == 1){


            val size = petner.users_logged_ln_last_week_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),petner.users_logged_ln_last_week_chart[x].count.toFloat()))
                xValueList.add("${petner.users_logged_ln_last_week_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "GÜNLÜK GİRİŞ"
            holder.itemDetails.text = petner.users_logged_ln_today_count.toString()


        }else if (position == 2){

            val size = petner.posts_in_last_five_months_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),petner.posts_in_last_five_months_chart[x].count.toFloat()))
                xValueList.add("${petner.posts_in_last_five_months_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "YENİ POST"
            holder.itemDetails.text = petner.posts_in_last_month_count.toString()

        }else if (position == 3) {

            val size = petner.conversations_in_last_five_weeks_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),petner.conversations_in_last_five_weeks_chart[x].count.toFloat()))
                xValueList.add("${petner.conversations_in_last_five_weeks_chart[x].created_at.subSequence(5,10)}")
            }

            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "CHAT SAYISI"
            holder.itemDetails.text = petner.conversations_in_last_week_count.toString()
        }
        else{

            val size = petner.comments_in_last_five_months_chart.size
            for (x in 0..size-1){
                barEntry.add(BarEntry(x.toFloat(),petner.comments_in_last_five_months_chart[x].count.toFloat()))
                xValueList.add("${petner.comments_in_last_five_months_chart[x].created_at.subSequence(5,10)}")
            }
            holder.chart.xAxis.valueFormatter = IndexAxisValueFormatter(xValueList)
            holder.itemTitle.text = "POST YORUM"
            holder.itemDetails.text = petner.comments_in_last_month_count.toString()

        }

        configureBarchart(holder,barEntry)

    }

    override fun getItemCount(): Int {
        return 5
    }


}