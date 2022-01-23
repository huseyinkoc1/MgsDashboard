package com.mgs.mgsdashboard.view.fragment.petner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.petner.RecyclerViewAdapterPetnerGorev
import com.mgs.mgsdashboard.adapter.petner.RecyclerViewAdapterPetnerKayit
import com.mgs.mgsdashboard.adapter.petner.ViewPagerAdapterPetner
import com.mgs.mgsdashboard.viewmodel.PetnerViewModel
import kotlinx.android.synthetic.main.fragment_petner.*
import kotlinx.android.synthetic.main.fragment_petner.circle
import kotlinx.android.synthetic.main.fragment_petner.edtTextKisi
import kotlinx.android.synthetic.main.fragment_petner.edtTextKullanici
import kotlinx.android.synthetic.main.fragment_petner.view_chart


class PetnerFragment : Fragment() {

    private lateinit var petnerViewModel: PetnerViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_petner, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        petnerViewModel = ViewModelProvider(this).get(PetnerViewModel::class.java)
        petnerViewModel.refreshData()


        petner_Kayit_RecyclerView.layoutManager =
            object : LinearLayoutManager(this.requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        petner_Gorev_RecyclerView.layoutManager =
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

                    view_chart.adapter = ViewPagerAdapterPetner(it!!)
                    view_chart.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                    circle.setViewPager(view_chart)
                    progressBar3.visibility = View.GONE

                    edtTextKullanici.text = it.users_count.toString() ?: ""
                    edtTextKisi.text = it.pets_count.toString() ?: ""
                    edtTextKisi2.text = it.adoption_pets_count.toString() ?: ""

                    textViewKayit.text = "Kayıt Olanlar (${it.register_users.size})" ?: ""
                    textViewOlay.text = "Son Olaylar (${it.logs.size})" ?: ""


                    petner_Kayit_RecyclerView.adapter = RecyclerViewAdapterPetnerKayit(it!!)
                    petner_Gorev_RecyclerView.adapter = RecyclerViewAdapterPetnerGorev(it!!)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        petnerViewModel.cleanDisposable()
    }

}