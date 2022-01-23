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
import com.mgs.mgsdashboard.adapter.avfast.AdapterAvfastLogs
import com.mgs.mgsdashboard.adapter.avfast.AdapterAvfastRegister
import com.mgs.mgsdashboard.adapter.avfast.AdapterAvfastChart
import com.mgs.mgsdashboard.databinding.FragmentAvfastBinding
import com.mgs.mgsdashboard.viewmodel.AvfastViewModel
import kotlinx.android.synthetic.main.fragment_avfast.*


class AvfastFragment : Fragment() {


    private lateinit var binding: FragmentAvfastBinding
    private lateinit var avfastViewModel: AvfastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAvfastBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avfastViewModel = ViewModelProvider(this).get(AvfastViewModel::class.java)
        avfastViewModel.refreshData()


        binding.avfastRegisterRecyclerView.layoutManager = object : LinearLayoutManager(this.requireContext()) { override fun canScrollVertically(): Boolean { return false } }
        binding.avfastLogsRecyclerView.layoutManager = object : LinearLayoutManager(this.requireContext()) { override fun canScrollVertically(): Boolean { return false } }

        observeAvfastData()

        progressBar2.visibility = View.VISIBLE

    }


    private fun observeAvfastData() {
        avfastViewModel.getAvfastListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    binding.viewChart.adapter = AdapterAvfastChart(it!!)
                    binding.viewChart.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    binding.circle.setViewPager(view_chart)
                    binding.progressBar2.visibility = View.GONE

                    binding.edtTextUser.text = it.users_count.toString() ?: ""
                    binding.edtTextOnlineUser.text = it?.online_users_count.toString() ?: ""

                    binding.textViewRegister.text = "Kayıt Olanlar (${it.register_users.size})" ?: ""
                    binding.textViewLogs.text = "Son Olaylar (${it.logs.size})" ?: ""

                    binding.avfastRegisterRecyclerView.adapter = AdapterAvfastRegister(it!!)
                    binding.avfastLogsRecyclerView.adapter = AdapterAvfastLogs(it!!)

                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        avfastViewModel.cleanDisposable()
    }
}