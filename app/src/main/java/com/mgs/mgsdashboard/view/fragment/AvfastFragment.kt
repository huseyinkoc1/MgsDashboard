package com.mgs.mgsdashboard.view.fragment




import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.RecyclerViewAdapterAvfastGorev
import com.mgs.mgsdashboard.adapter.RecyclerViewAdapterAvfastKayit
import com.mgs.mgsdashboard.adapter.ViewPagerAdapterAvfast
import com.mgs.mgsdashboard.model.avfastApi.Avfast
import com.mgs.mgsdashboard.model.avfastApi.MonthlyTotalUsersChart
import com.mgs.mgsdashboard.viewmodel.AvfastViewModel
import kotlinx.android.synthetic.main.avfast_dashboard.*
import kotlinx.android.synthetic.main.fragment_avfast.*



class AvfastFragment : Fragment() {


    private lateinit var avfastViewModel: AvfastViewModel
    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<Int>()
    private var avfastModels: Avfast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun addToList(title: String ,description: Int){
        titleList.add(title)
        descList.add(description)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_avfast, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avfastViewModel = ViewModelProvider(this).get(AvfastViewModel::class.java)
        avfastViewModel.refreshData()


        avfast_Kayit_RecyclerView.layoutManager = object  : LinearLayoutManager(this.requireContext()){ override fun canScrollVertically(): Boolean { return false } }
        avfast_Gorev_RecyclerView.layoutManager = object  : LinearLayoutManager(this.requireContext()){ override fun canScrollVertically(): Boolean { return false } }

        observeAvfastData()

        progressBar2.visibility = View.VISIBLE

    }


    private fun observeAvfastData() {
        avfastViewModel.getAvfastListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {
                    avfastModels = it

                    addToList("BU AY KAYITLI KULLANICI",it.monthly_total_users_count)
                    addToList("GÜNLÜK GİRİŞ",it.daily_logged_in_users_count)
                    addToList("YENİ TASK",it.weekly_tasks_count)
                    addToList("BAŞVURMA",it.weekly_applied_tasks_count)
                    addToList("TAMAMLANAN",it.weekly_done_tasks_count)
                    addToList("DEĞERLENDİRME",it.weekly_evaluated_tasks_count)

                    view_pager2.adapter = ViewPagerAdapterAvfast(titleList,descList, it!!)
                    view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    circle.setViewPager(view_pager2)

                    progressBar2.visibility = View.GONE
                    edtTextKullanici.text = it.users_count.toString() ?:""
                    edtTextKisi.text = it?.online_users_count.toString() ?:""
                    textViewKayitSayi.text = "Kayıt Olanlar (${it.register_users.size})" ?:""
                    textViewOlaySayisi.text = "Son Olaylar (${it.logs.size})" ?:""
                    avfast_Kayit_RecyclerView.adapter = RecyclerViewAdapterAvfastKayit(it!!)
                    avfast_Gorev_RecyclerView.adapter = RecyclerViewAdapterAvfastGorev(it!!)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        avfastViewModel.cleanDisposable()
    }
}