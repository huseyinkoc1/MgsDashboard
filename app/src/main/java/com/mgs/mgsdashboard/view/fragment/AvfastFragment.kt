package com.mgs.mgsdashboard.view.fragment




import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.mgs.mgsdashboard.R
import com.mgs.mgsdashboard.adapter.RecyclerViewAdapterAvfastGorev
import com.mgs.mgsdashboard.adapter.RecyclerViewAdapterAvfastKayit
import com.mgs.mgsdashboard.adapter.ViewPagerAdapterAvfast
import com.mgs.mgsdashboard.model.avfastApi.Avfast
import com.mgs.mgsdashboard.service.AvFastAPI
import kotlinx.android.synthetic.main.fragment_avfast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AvfastFragment : Fragment() {

    private val BASE_URL = "https://staging-api.avfast.site/"
    private val PATH_NAME = "api/v1/"
    private val DOMAIN_URL = BASE_URL+PATH_NAME
    private var avfastModels: Avfast?= null
    private  var recyclerViewAdapterKayit : RecyclerViewAdapterAvfastKayit? = null
    private  var recyclerViewAdapterGorev : RecyclerViewAdapterAvfastGorev? = null

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imgList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun addToList(title: String,description: String, image: Int){
        titleList.add(title)
        descList.add(description)
        imgList.add(image)
    }

    private  fun postToList(){
        addToList("BU AY KAYITLI KULLANICI","10",R.drawable.dash)
        addToList("GÜNLÜK GİRİŞ","10",R.drawable.dash)
        addToList("YENİ TASK","9",R.drawable.dash)
        addToList("BAŞVURMA","7",R.drawable.dash)
        addToList("TAMAMLANAN","3",R.drawable.dash)
        addToList("DEĞERLENDİRME","3",R.drawable.dash)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_avfast, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()
        view_pager2.adapter = ViewPagerAdapterAvfast(titleList,descList,imgList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        circle.setViewPager(view_pager2)

        avfast_Kayit_RecyclerView.layoutManager = object  : LinearLayoutManager(this.requireContext()){ override fun canScrollVertically(): Boolean { return false } }
        avfast_Gorev_RecyclerView.layoutManager = object  : LinearLayoutManager(this.requireContext()){ override fun canScrollVertically(): Boolean { return false } }

        //var adapter = RecyclerAdapterAvFastKayit(this.requireContext(), Helper.getVersionsList())
        //var adapterGorev = RecyclerAdapterAvFastKayit(this.requireContext(), Helper.getVersionsListGorev())


        //avfast_Kayit_RecyclerView.adapter = adapter
        loadData()

        //avfast_Gorev_RecyclerView.adapter = adapterGorev


    }



     fun loadData(){
         val retrofit = Retrofit.Builder()
             .baseUrl(DOMAIN_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         val service = retrofit.create(AvFastAPI::class.java)
         val call = service.getData()

        call.enqueue(object : Callback<Avfast>{
            override fun onResponse(call: Call<Avfast>, response: Response<Avfast>) {
               if (response.isSuccessful){
                   response.body()?.let {
                       avfastModels = it

                       edtTextKullanici.text = avfastModels!!.users_count.toString()
                       edtTextKisi.text = avfastModels!!.online_users_count.toString()

                       recyclerViewAdapterKayit = RecyclerViewAdapterAvfastKayit(avfastModels!!)
                       avfast_Kayit_RecyclerView.adapter = recyclerViewAdapterKayit

                       recyclerViewAdapterGorev = RecyclerViewAdapterAvfastGorev(avfastModels!!)
                       avfast_Gorev_RecyclerView.adapter = recyclerViewAdapterGorev

                   }
               }
            }

            override fun onFailure(call: Call<Avfast>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }
}