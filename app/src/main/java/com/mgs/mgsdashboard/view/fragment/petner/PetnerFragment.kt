package com.mgs.mgsdashboard.view.fragment.petner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.adapter.petner.AdapterPetnerLogs
import com.mgs.mgsdashboard.adapter.petner.AdapterPetnerRegister
import com.mgs.mgsdashboard.adapter.petner.AdapterPetnerChart
import com.mgs.mgsdashboard.databinding.FragmentPetnerBinding
import com.mgs.mgsdashboard.viewmodel.PetnerViewModel
import kotlinx.android.synthetic.main.fragment_petner.*
import kotlinx.android.synthetic.main.fragment_petner.view_chart


class PetnerFragment : Fragment() {

    private lateinit var petnerViewModel: PetnerViewModel
    private lateinit var binding: FragmentPetnerBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPetnerBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        petnerViewModel = ViewModelProvider(this).get(PetnerViewModel::class.java)
        petnerViewModel.refreshData()


        binding.petnerRegisterRecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        binding.petnerLogsRecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

        observePetnerData()
        progressBar3.visibility = View.VISIBLE
    }

    private fun observePetnerData() {
        petnerViewModel.getPetnerListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    binding.viewChart.adapter = AdapterPetnerChart(it!!)
                    binding.viewChart.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    binding.circle.setViewPager(view_chart)
                    binding.progressBar3.visibility = View.GONE

                    binding.edtTextUsersCount.text = it.users_count.toString() ?: ""
                    binding.edtTextPetsCount.text = it.pets_count.toString() ?: ""
                    binding.edtTextAdoptionPetsCount.text = it.adoption_pets_count.toString() ?: ""

                    binding.textViewRegister.text = "Kayıt Olanlar (${it.register_users.size})" ?: ""
                    binding.textViewLogs.text = "Son Olaylar (${it.logs.size})" ?: ""


                    binding.petnerRegisterRecyclerView.adapter = AdapterPetnerRegister(it!!)
                    binding.petnerLogsRecyclerView.adapter = AdapterPetnerLogs(it!!)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        petnerViewModel.cleanDisposable()
    }

}