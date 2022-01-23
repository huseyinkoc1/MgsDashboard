package com.mgs.mgsdashboard.view.fragment.redmine

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.redmine.AdapterOpenedTask
import com.mgs.mgsdashboard.adapter.redmine.AdapterOpenedTaskSecond
import com.mgs.mgsdashboard.model.redmine.TaskCreated
import com.mgs.mgsdashboard.utils.RoundedBarChart
import com.mgs.mgsdashboard.viewmodel.RedmineViewModel
import kotlinx.android.synthetic.main.fragment_opened_task.*


class FragmentOpenedTask : Fragment() {

    private lateinit var redmineViewModel: RedmineViewModel
    var listRed: Array<TaskCreated>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_opened_task, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        redmineViewModel = ViewModelProvider(this).get(RedmineViewModel::class.java)
        redmineViewModel.refreshData()

        taskCompletion_RecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        taskCompletion_RecyclerView2.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return true
                }
            }

        observeRedmineData()
        progressBarRedmine2.visibility = View.VISIBLE

    }


    private fun observeRedmineData() {
        redmineViewModel.getRedmineListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    val defaultValue = TaskCreated("", "", 0.0)
                    listRed = Array<TaskCreated>(it.task_created.size) { defaultValue }

                    listRed = it.task_created.toTypedArray()

                    fun sortBubble(array: Array<TaskCreated>): Array<TaskCreated> {
                        var temp: TaskCreated

                        for (i in 0 until array.size) {
                            for (j in 0 until array.size - i - 1) {

                                if (array[j].points < array[j + 1].points) {
                                    temp = array[j]
                                    array[j] = array[j + 1]
                                    array[j + 1] = temp
                                }
                            }
                        }

                        return array
                    }

                    listRed = sortBubble(listRed!!)

                    for (x in 0..it.task_created.size - 1) {
                        println(listRed!![x].image_url + "  " + listRed!![x].name + "  " + listRed!![x].points)
                    }

                    val adapter = AdapterOpenedTask(listRed!!)
                    taskCompletion_RecyclerView.adapter = adapter

                    val adapterSecond = AdapterOpenedTaskSecond(listRed!!)
                    taskCompletion_RecyclerView2.adapter = adapterSecond

                    configureChart()
                    progressBarRedmine2.visibility = View.GONE
                }
            })
    }


    fun configureChart() {
        val barEntry = arrayListOf<BarEntry>()
        val xValueModel = arrayOf(
            listRed!![0].name.split(' ')[0],
            listRed!![1].name.split(' ')[0],
            listRed!![2].name.split(' ')[0],
            listRed!![3].name.split(' ')[0],
            listRed!![4].name.split(' ')[0]
        )



        barEntry.add(BarEntry(0f, listRed!![0].points.toFloat()))
        barEntry.add(BarEntry(1f, listRed!![1].points.toFloat()))
        barEntry.add(BarEntry(2f, listRed!![2].points.toFloat()))
        barEntry.add(BarEntry(3f, listRed!![3].points.toFloat()))
        barEntry.add(BarEntry(4f, listRed!![4].points.toFloat()))


        val barDataSet = BarDataSet(barEntry, "Storypoint")
        barDataSet.valueTextColor = Color.parseColor("#727272")
        barDataSet.setColors(Color.parseColor("#C1DDFF"))
        barDataSet.isHighlightEnabled = false


        barChartTaskCompletion.axisRight.isEnabled = false
        barChartTaskCompletion.legend.isEnabled = true
        barChartTaskCompletion.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        barChartTaskCompletion.legend.textSize = 10f
        barChartTaskCompletion.legend.textColor = Color.parseColor("#444444")
        barChartTaskCompletion.legend.formToTextSpace = 15f
        barChartTaskCompletion.legend.typeface = Typeface.MONOSPACE
        barChartTaskCompletion.description.isEnabled = false
        barChartTaskCompletion.extraBottomOffset = 15f




        barChartTaskCompletion.xAxis.textColor = Color.parseColor("#444444")
        barChartTaskCompletion.xAxis.textSize = 10f
        barChartTaskCompletion.xAxis.axisLineColor = Color.parseColor("#3D7CC9")
        barChartTaskCompletion.xAxis.granularity = 1f
        barChartTaskCompletion.xAxis.axisLineWidth = 1f
        barChartTaskCompletion.xAxis.typeface = Typeface.MONOSPACE
        barChartTaskCompletion.xAxis.setCenterAxisLabels(false)



        barChartTaskCompletion.axisLeft.textColor = Color.parseColor("#444444")
        barChartTaskCompletion.axisLeft.textSize = 8f
        barChartTaskCompletion.axisLeft.axisLineColor = Color.parseColor("#3D7CC9")
        barChartTaskCompletion.axisLeft.axisLineWidth = 1f
        barChartTaskCompletion.axisLeft.setStartAtZero(true)
        barChartTaskCompletion.axisLeft.setCenterAxisLabels(false)
        barChartTaskCompletion.axisLeft.typeface = Typeface.MONOSPACE

        barChartTaskCompletion.renderer = RoundedBarChart(
            barChartTaskCompletion,
            barChartTaskCompletion.animator,
            barChartTaskCompletion.viewPortHandler
        )

        val barData = BarData(barDataSet)
        barChartTaskCompletion.animateXY(2000, 1800)
        barChartTaskCompletion.setFitBars(true)
        barChartTaskCompletion.data = barData
        barData.barWidth = 0.5f
        barChartTaskCompletion.xAxis.valueFormatter = IndexAxisValueFormatter(xValueModel)
        barChartTaskCompletion.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChartTaskCompletion.invalidate()

    }

    override fun onDestroy() {
        super.onDestroy()
        //redmineViewModel.cleanDisposable()
    }
}