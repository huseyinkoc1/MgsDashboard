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
import com.mgs.mgsdashboard.adapter.RecyclerViewAdapterAvfastGorev
import com.mgs.mgsdashboard.adapter.RecyclerViewAdapterAvfastKayit
import com.mgs.mgsdashboard.adapter.ViewPagerAdapterAvfast
import com.mgs.mgsdashboard.model.avfastApi.Avfast
import com.mgs.mgsdashboard.viewmodel.AvfastViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_avfast.*



class AvfastFragment : Fragment() {


    private  var recyclerViewAdapterKayit : RecyclerViewAdapterAvfastKayit? = null
    private  var recyclerViewAdapterGorev : RecyclerViewAdapterAvfastGorev? = null
    private lateinit var avfastViewModel: AvfastViewModel
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

        avfastViewModel = ViewModelProvider(this).get(AvfastViewModel::class.java)
        avfastViewModel.refreshData()

        postToList()
        view_pager2.adapter = ViewPagerAdapterAvfast(titleList,descList,imgList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        circle.setViewPager(view_pager2)

        avfast_Kayit_RecyclerView.layoutManager = object  : LinearLayoutManager(this.requireContext()){ override fun canScrollVertically(): Boolean { return false } }
        avfast_Gorev_RecyclerView.layoutManager = object  : LinearLayoutManager(this.requireContext()){ override fun canScrollVertically(): Boolean { return false } }

        observeAvfastData()




    }


    private fun observeAvfastData() {
        avfastViewModel.getAvfastListLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer {
                it?.let {

                    edtTextKullanici.text = it.users_count.toString() ?:""
                    edtTextKisi.text = it?.online_users_count.toString() ?:""

                    recyclerViewAdapterKayit = RecyclerViewAdapterAvfastKayit(it!!)
                    avfast_Kayit_RecyclerView.adapter = recyclerViewAdapterKayit

                    recyclerViewAdapterGorev = RecyclerViewAdapterAvfastGorev(it!!)
                    avfast_Gorev_RecyclerView.adapter = recyclerViewAdapterGorev
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        avfastViewModel.cleanDisposable()
    }
}