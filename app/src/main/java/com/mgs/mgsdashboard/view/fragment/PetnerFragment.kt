package com.mgs.mgsdashboard.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.*
import com.mgs.mgsdashboard.model.petnerApi.Petner
import com.mgs.mgsdashboard.service.PetnerAPI
import kotlinx.android.synthetic.main.fragment_petner.*
import kotlinx.android.synthetic.main.fragment_petner.circle
import kotlinx.android.synthetic.main.fragment_petner.view_pager2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PetnerFragment : Fragment() {

    private val BASE_URL = "https://staging-api.petner.net/"
    private val PATH_NAME = "api/v1/"
    private val DOMAIN_URL = BASE_URL + PATH_NAME
    private var petnerModels: Petner? = null
    private var recyclerViewAdapterKayit: RecyclerViewAdapterPetnerKayit? = null
    private var recyclerViewAdapterGorev: RecyclerViewAdapterPetnerGorev? = null

    var counter = 0

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imgList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title)
        descList.add(description)
        imgList.add(image)
    }

    private fun postToList() {
        addToList("BU AY KAYITLI KULLANICI", "10", R.drawable.dashp)
        addToList("GÜNLÜK GİRİŞ", "10", R.drawable.dashp)
        addToList("YENİ TASK", "9", R.drawable.dashp)
        addToList("BAŞVURMA", "7", R.drawable.dashp)
        addToList("TAMAMLANAN", "3", R.drawable.dashp)
        addToList("DEĞERLENDİRME", "3", R.drawable.dashp)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_petner, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()
        view_pager2.adapter = ViewPagerAdapterPetner(titleList, descList, imgList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        circle.setViewPager(view_pager2)



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

        loadData()

        if (counter != 0) {
            //var adapter = RecyclerAdapterAvFastKayit(requireContext(), Helper.getVersionsListPetner())
            //petner_Kayit_RecyclerView.adapter = adapter

            //var adapterGorev = RecyclerAdapterAvFastKayit(requireContext(), Helper.getVersionsListPetnerGorev())
            //petner_Gorev_RecyclerView.adapter = adapterGorev


        }


    }


    override fun onResume() {
        super.onResume()

        if (counter == 0) {
            //var adapter = RecyclerAdapterAvFastKayit(requireContext(), Helper.getVersionsListPetner())
            //petner_Kayit_RecyclerView.adapter = adapter

            //var adapterGorev = RecyclerAdapterAvFastKayit(requireContext(), Helper.getVersionsListPetnerGorev())
            //petner_Gorev_RecyclerView.adapter = adapterGorev

            counter++
        }
    }

    fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(DOMAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PetnerAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<Petner> {
            override fun onResponse(call: Call<Petner>, response: Response<Petner>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        petnerModels = it

                        edtTextKullanici.text = petnerModels!!.users_count.toString()
                        edtTextKisi.text = petnerModels!!.pets_count.toString()
                        edtTextKisi2.text = petnerModels!!.adoption_pets_count.toString()

                        recyclerViewAdapterKayit = RecyclerViewAdapterPetnerKayit(petnerModels!!)
                        petner_Kayit_RecyclerView.adapter = recyclerViewAdapterKayit

                        recyclerViewAdapterGorev = RecyclerViewAdapterPetnerGorev(petnerModels!!)
                        petner_Gorev_RecyclerView.adapter = recyclerViewAdapterGorev
                    }
                }
            }

            override fun onFailure(call: Call<Petner>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}