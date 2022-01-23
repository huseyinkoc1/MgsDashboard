package com.mgs.mgsdashboard.view.fragment.avfast


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.avfast.RecyclerViewAdapterAvfastGorev
import com.mgs.mgsdashboard.adapter.avfast.RecyclerViewAdapterAvfastKayit
import com.mgs.mgsdashboard.adapter.avfast.ViewPagerAdapterAvfast
import com.mgs.mgsdashboard.viewmodel.AvfastViewModel
import kotlinx.android.synthetic.main.fragment_avfast.*


class AvfastFragment : Fragment() {


    private lateinit var avfastViewModel: AvfastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_avfast, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avfastViewModel = ViewModelProvider(this).get(AvfastViewModel::class.java)
        avfastViewModel.refreshData()


        avfast_Kayit_RecyclerView.layoutManager = object : LinearLayoutManager(this.requireContext()) { override fun canScrollVertically(): Boolean { return false } }
        avfast_Gorev_RecyclerView.layoutManager = object : LinearLayoutManager(this.requireContext()) { override fun canScrollVertically(): Boolean { return false } }

        observeAvfastData()

        progressBar2.visibility = View.VISIBLE

    }


    private fun observeAvfastData() {
        avfastViewModel.getAvfastListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    view_chart.adapter = ViewPagerAdapterAvfast(it!!)
                    view_chart.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    circle.setViewPager(view_chart)
                    progressBar2.visibility = View.GONE

                    edtTextKullanici.text = it.users_count.toString() ?: ""
                    edtTextKisi.text = it?.online_users_count.toString() ?: ""

                    textViewKayitSayi.text = "Kayıt Olanlar (${it.register_users.size})" ?: ""
                    textViewOlaySayisi.text = "Son Olaylar (${it.logs.size})" ?: ""

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