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
import com.mgs.mgsdashboard.databinding.FragmentCompletedTeamBinding
import com.mgs.mgsdashboard.model.redmine.TaskCompletedTeam
import com.mgs.mgsdashboard.utils.RoundedBarChart
import com.mgs.mgsdashboard.viewmodel.RedmineViewModel
import kotlinx.android.synthetic.main.fragment_completed_team.*


class FragmentCompletedTeam : Fragment() {

    private lateinit var redmineViewModel: RedmineViewModel
    private lateinit var binding: FragmentCompletedTeamBinding

    var listRed: Array<TaskCompletedTeam>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCompletedTeamBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        redmineViewModel = ViewModelProvider(this).get(RedmineViewModel::class.java)
        redmineViewModel.refreshData()


        binding.openedTaskRecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        binding.openedTaskRecyclerView2.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return true
                }
            }



        observeRedmineData()

        binding.progressBarRedmine3.visibility = View.VISIBLE

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


                    val adapter = AdapterCompletedTeam(listRed!!)
                    binding.openedTaskRecyclerView.adapter = adapter

                    val adapterSecond = AdapterCompletedTeamSecond(listRed!!)
                    binding.openedTaskRecyclerView2.adapter = adapterSecond

                    configureChart()
                    binding.progressBarRedmine3.visibility = View.GONE

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


        binding.barChartOpenedTask.axisRight.isEnabled = false
        binding.barChartOpenedTask.legend.isEnabled = true
        binding.barChartOpenedTask.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        binding.barChartOpenedTask.legend.textSize = 10f
        binding.barChartOpenedTask.legend.textColor = Color.parseColor("#444444")
        binding.barChartOpenedTask.legend.formToTextSpace = 15f
        binding.barChartOpenedTask.legend.typeface = Typeface.MONOSPACE
        binding.barChartOpenedTask.description.isEnabled = false
        binding.barChartOpenedTask.extraBottomOffset = 15f


        binding.barChartOpenedTask.xAxis.textColor = Color.parseColor("#444444")
        binding.barChartOpenedTask.xAxis.textSize = 10f
        binding.barChartOpenedTask.xAxis.axisLineColor = Color.parseColor("#3D7CC9")
        binding.barChartOpenedTask.xAxis.granularity = 1f
        binding.barChartOpenedTask.xAxis.axisLineWidth = 1f
        binding.barChartOpenedTask.xAxis.typeface = Typeface.MONOSPACE
        binding.barChartOpenedTask.xAxis.setCenterAxisLabels(false)


        binding.barChartOpenedTask.axisLeft.textColor = Color.parseColor("#444444")
        binding.barChartOpenedTask.axisLeft.textSize = 10f
        binding.barChartOpenedTask.axisLeft.axisLineColor = Color.parseColor("#3D7CC9")
        binding.barChartOpenedTask.axisLeft.axisLineWidth = 1f
        binding.barChartOpenedTask.axisLeft.setStartAtZero(true)
        binding.barChartOpenedTask.axisLeft.setCenterAxisLabels(false)
        binding.barChartOpenedTask.axisLeft.typeface = Typeface.MONOSPACE

        binding.barChartOpenedTask.renderer = RoundedBarChart(
            barChartOpenedTask,
            barChartOpenedTask.animator,
            barChartOpenedTask.viewPortHandler
        )

        val barData = BarData(barDataSet)
        binding.barChartOpenedTask.animateXY(2000, 1800)
        binding.barChartOpenedTask.setFitBars(true)
        binding.barChartOpenedTask.data = barData
        barData.barWidth = 0.5f
        binding.barChartOpenedTask.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        binding.barChartOpenedTask.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.barChartOpenedTask.invalidate()

    }

    override fun onDestroy() {
        super.onDestroy()
        //redmineViewModel.cleanDisposable()
    }

}