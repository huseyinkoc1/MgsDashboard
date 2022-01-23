package com.mgs.mgsdashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mgs.mgsdashboard.model.avfast.Avfast
import com.mgs.mgsdashboard.service.AvfastService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AvfastViewModel: ViewModel() {

    private val avfastApiService = AvfastService()
    private val disposable = CompositeDisposable()
    val getAvfastListLiveData = MutableLiveData<Avfast>()

    fun refreshData() {
        getAvfastData()
    }

    fun cleanDisposable(){
        disposable.clear()
    }

    fun getAvfastData() {
        disposable.add(
            avfastApiService.getData()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Avfast>() {
                    override fun onSuccess(t : Avfast) {
                        getAvfastListLiveData.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}