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
import com.mgs.mgsdashboard.adapter.redmine.AdapterCompletedTeam
import com.mgs.mgsdashboard.adapter.redmine.AdapterCompletedTeamSecond
import com.mgs.mgsdashboard.model.redmine.TaskCompletedTeam
import com.mgs.mgsdashboard.utils.RoundedBarChart
import com.mgs.mgsdashboard.viewmodel.RedmineViewModel
import kotlinx.android.synthetic.main.fragment_completed_team.*


class FragmentCompletedTeam : Fragment() {

    private lateinit var redmineViewModel: RedmineViewModel

    var listRed: Array<TaskCompletedTeam>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        redmineViewModel = ViewModelProvider(this).get(RedmineViewModel::class.java)
        redmineViewModel.refreshData()


        openedTask_RecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        openedTask_RecyclerView2.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return true
                }
            }



        observeRedmineData()

        progressBarRedmine3.visibility = View.VISIBLE

    }


    private fun observeRedmineData() {
        redmineViewModel.getRedmineListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    val defaultValue = TaskCompletedTeam("", 0.0)
                    listRed = Array<TaskCompletedTeam>(it.task_completed_team.size) { defaultValue }

                    listRed = it.task_completed_team.toTypedArray()

                    fun sortBubble(array: Array<TaskCompletedTeam>): Array<TaskCompletedTeam> {
                        var temp: TaskCompletedTeam

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

                    for (x in 0..it.task_completed_team.size - 1) {
                        println(listRed!![x].name + "  " + listRed!![x].points)
                    }

                    val adapter = AdapterCompletedTeam(listRed!!)
                    openedTask_RecyclerView.adapter = adapter

                    val adapterSecond = AdapterCompletedTeamSecond(listRed!!)
                    openedTask_RecyclerView2.adapter = adapterSecond

                    configureChart()
                    progressBarRedmine3.visibility = View.GONE

                }
            })
    }


    fun configureChart() {
        val barEntry = arrayListOf<BarEntry>()
        val arabaModel = arrayOf(
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


        barChartOpenedTask.axisRight.isEnabled = false
        barChartOpenedTask.legend.isEnabled = true
        barChartOpenedTask.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        barChartOpenedTask.legend.textSize = 10f
        barChartOpenedTask.legend.textColor = Color.parseColor("#444444")
        barChartOpenedTask.legend.formToTextSpace = 15f
        barChartOpenedTask.legend.typeface = Typeface.MONOSPACE
        barChartOpenedTask.description.isEnabled = false
        barChartOpenedTask.extraBottomOffset = 15f


        barChartOpenedTask.xAxis.textColor = Color.parseColor("#444444")
        barChartOpenedTask.xAxis.textSize = 10f
        barChartOpenedTask.xAxis.axisLineColor = Color.parseColor("#3D7CC9")
        barChartOpenedTask.xAxis.granularity = 1f
        barChartOpenedTask.xAxis.axisLineWidth = 1f
        barChartOpenedTask.xAxis.typeface = Typeface.MONOSPACE
        barChartOpenedTask.xAxis.setCenterAxisLabels(false)


        barChartOpenedTask.axisLeft.textColor = Color.parseColor("#444444")
        barChartOpenedTask.axisLeft.textSize = 10f
        barChartOpenedTask.axisLeft.axisLineColor = Color.parseColor("#3D7CC9")
        barChartOpenedTask.axisLeft.axisLineWidth = 1f
        barChartOpenedTask.axisLeft.setStartAtZero(true)
        barChartOpenedTask.axisLeft.setCenterAxisLabels(false)
        barChartOpenedTask.axisLeft.typeface = Typeface.MONOSPACE

        barChartOpenedTask.renderer = RoundedBarChart(
            barChartOpenedTask,
            barChartOpenedTask.animator,
            barChartOpenedTask.viewPortHandler
        )

        val barData = BarData(barDataSet)
        barChartOpenedTask.animateXY(2000, 1800)
        barChartOpenedTask.setFitBars(true)
        barChartOpenedTask.data = barData
        barData.barWidth = 0.5f
        barChartOpenedTask.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        barChartOpenedTask.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChartOpenedTask.invalidate()

    }

    override fun onDestroy() {
        super.onDestroy()
        //redmineViewModel.cleanDisposable()
    }

}