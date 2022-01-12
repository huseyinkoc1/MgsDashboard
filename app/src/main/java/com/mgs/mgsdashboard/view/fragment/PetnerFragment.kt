package com.mgs.mgsdashboard.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.*
import com.mgs.mgsdashboard.viewmodel.PetnerViewModel
import kotlinx.android.synthetic.main.fragment_avfast.*
import kotlinx.android.synthetic.main.fragment_petner.*
import kotlinx.android.synthetic.main.fragment_petner.circle
import kotlinx.android.synthetic.main.fragment_petner.edtTextKisi
import kotlinx.android.synthetic.main.fragment_petner.edtTextKullanici
import kotlinx.android.synthetic.main.fragment_petner.view_pager2


class PetnerFragment : Fragment() {

//    private val BASE_URL = "https://staging-api.petner.net/"
//    private val PATH_NAME = "api/v1/"
//    private val DOMAIN_URL = BASE_URL + PATH_NAME
//    private var petnerModels: Petner? = null
    //private var recyclerViewAdapterKayit: RecyclerViewAdapterPetnerKayit? = null
    //private var recyclerViewAdapterGorev: RecyclerViewAdapterPetnerGorev? = null
//    private var compositeDisposable : CompositeDisposable? = null
    private lateinit var petnerViewModel: PetnerViewModel

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    private fun addToList(title: String, description: Int) {
        titleList.add(title)
        descList.add(description)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_petner, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        petnerViewModel = ViewModelProvider(this).get(PetnerViewModel::class.java)
        petnerViewModel.refreshData()





        petner_Kayit_RecyclerView.layoutManager = object : LinearLayoutManager(this.requireContext()) { override fun canScrollVertically(): Boolean { return false } }
        petner_Gorev_RecyclerView.layoutManager = object : LinearLayoutManager(this.requireContext()) { override fun canScrollVertically(): Boolean { return false } }

        //compositeDisposable = CompositeDisposable()

        observePetnerData()

        progressBar3.visibility = View.VISIBLE
        //loadData()


    }

    /*fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(DOMAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PetnerAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this:: handleResponse))

    }

    private fun handleResponse(petner: Petner){
        petnerModels = petner

        edtTextKullanici.text = petnerModels!!.users_count.toString()
        edtTextKisi.text = petnerModels!!.pets_count.toString()
        edtTextKisi2.text = petnerModels!!.adoption_pets_count.toString()

        recyclerViewAdapterKayit = RecyclerViewAdapterPetnerKayit(petnerModels!!)
        petner_Kayit_RecyclerView.adapter = recyclerViewAdapterKayit

        recyclerViewAdapterGorev = RecyclerViewAdapterPetnerGorev(petnerModels!!)
        petner_Gorev_RecyclerView.adapter = recyclerViewAdapterGorev

    }*/

    private fun observePetnerData() {
        petnerViewModel.getPetnerListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    addToList("BU AY KAYITLI KULLANICI",it.users_count)
                    addToList("GÜNLÜK GİRİŞ",it.users_logged_ln_today_count)
                    addToList("YENİ POST", it.posts_in_last_month_count)
                    addToList("CHAT SAYISI",  it.conversations_in_last_week_count)
                    addToList("POST YORUM", it.comments_in_last_month_count)



                    view_pager2.adapter = ViewPagerAdapterPetner(titleList, descList,it!!)
                    view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    circle.setViewPager(view_pager2)

                    progressBar3.visibility = View.GONE
                    edtTextKullanici.text = it.users_count.toString() ?:""
                    edtTextKisi.text = it.pets_count.toString() ?:""
                    edtTextKisi2.text = it.adoption_pets_count.toString() ?:""
                    textViewKayit.text = "Kayıt Olanlar (${it.register_users.size})" ?:""
                    textViewOlay.text = "Son Olaylar (${it.logs.size})" ?:""

                    //recyclerViewAdapterKayit = RecyclerViewAdapterPetnerKayit(it!!)
                    petner_Kayit_RecyclerView.adapter = RecyclerViewAdapterPetnerKayit(it!!)

                    //recyclerViewAdapterGorev = RecyclerViewAdapterPetnerGorev(it!!)
                    petner_Gorev_RecyclerView.adapter = RecyclerViewAdapterPetnerGorev(it!!)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        petnerViewModel.cleanDisposable()
    }

}