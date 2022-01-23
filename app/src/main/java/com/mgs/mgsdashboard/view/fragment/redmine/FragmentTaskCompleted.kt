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
import com.mgs.mgsdashboard.adapter.redmine.AdapterTaskCompleted
import com.mgs.mgsdashboard.adapter.redmine.AdapterTaskCompletedSecond
import com.mgs.mgsdashboard.databinding.FragmentTaskCompletedBinding
import com.mgs.mgsdashboard.model.redmine.TaskCompleted
import com.mgs.mgsdashboard.utils.RoundedBarChart
import com.mgs.mgsdashboard.viewmodel.RedmineViewModel
import kotlinx.android.synthetic.main.fragment_task_completed.*


class FragmentTaskCompleted : Fragment() {


    private lateinit var redmineViewModel: RedmineViewModel
    private lateinit var binding: FragmentTaskCompletedBinding
    var listRed: Array<TaskCompleted>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskCompletedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        redmineViewModel = ViewModelProvider(this).get(RedmineViewModel::class.java)
        redmineViewModel.refreshData()

        binding.storyPointRecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        binding.storyPointRecyclerView2.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return true
                }
            }


        observeRedmineData()
        progressBarRedMine.visibility = View.VISIBLE
    }


    private fun observeRedmineData() {
        redmineViewModel.getRedmineListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    val defaultValue = TaskCompleted("", "", 0.0)
                    listRed = Array<TaskCompleted>(it.task_completed.size) { defaultValue }

                    listRed = it.task_completed.toTypedArray()

                    fun sortBubble(array: Array<TaskCompleted>): Array<TaskCompleted> {
                        var temp: TaskCompleted

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


                    val adapter = AdapterTaskCompleted(listRed!!)
                    binding.storyPointRecyclerView.adapter = adapter


                    val adapterSecond = AdapterTaskCompletedSecond(listRed!!)
                    binding.storyPointRecyclerView2.adapter = adapterSecond

                    configureChart()
                    binding.progressBarRedMine.visibility = View.GONE
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



        binding.barChartTaskCompletion.axisRight.isEnabled = false
        binding.barChartTaskCompletion.legend.isEnabled = true
        binding.barChartTaskCompletion.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        binding.barChartTaskCompletion.legend.textSize = 8f
        binding.barChartTaskCompletion.legend.textColor = Color.parseColor("#444444")
        binding.barChartTaskCompletion.legend.formToTextSpace = 15f
        binding.barChartTaskCompletion.legend.typeface = Typeface.MONOSPACE
        binding.barChartTaskCompletion.description.isEnabled = false
        binding.barChartTaskCompletion.extraBottomOffset = 15f



        binding.barChartTaskCompletion.xAxis.textColor = Color.parseColor("#444444")
        binding.barChartTaskCompletion.xAxis.textSize = 10f
        binding.barChartTaskCompletion.xAxis.axisLineColor = Color.parseColor("#3D7CC9")
        binding.barChartTaskCompletion.xAxis.granularity = 1f
        binding.barChartTaskCompletion.xAxis.axisLineWidth = 1f
        binding.barChartTaskCompletion.xAxis.typeface = Typeface.MONOSPACE
        binding.barChartTaskCompletion.xAxis.setCenterAxisLabels(false)


        binding.barChartTaskCompletion.axisLeft.textColor = Color.parseColor("#444444")
        binding.barChartTaskCompletion.axisLeft.textSize = 10f
        binding.barChartTaskCompletion.axisLeft.axisLineColor = Color.parseColor("#3D7CC9")
        binding.barChartTaskCompletion.axisLeft.axisLineWidth = 1f
        binding.barChartTaskCompletion.axisLeft.setStartAtZero(true)
        binding.barChartTaskCompletion.axisLeft.setCenterAxisLabels(false)
        binding.barChartTaskCompletion.axisLeft.typeface = Typeface.MONOSPACE

        binding.barChartTaskCompletion.renderer = RoundedBarChart(
            barChartTaskCompletion,
            barChartTaskCompletion.animator,
            barChartTaskCompletion.viewPortHandler
        )

        val barData = BarData(barDataSet)
        binding.barChartTaskCompletion.animateXY(2000, 1800)
        binding.barChartTaskCompletion.setFitBars(true)
        binding.barChartTaskCompletion.data = barData
        barData.barWidth = 0.5f
        binding.barChartTaskCompletion.xAxis.valueFormatter = IndexAxisValueFormatter(arabaModel)
        binding.barChartTaskCompletion.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.barChartTaskCompletion.invalidate()

    }

    override fun onDestroy() {
        super.onDestroy()
        //redmineViewModel.cleanDisposable()
    }

}