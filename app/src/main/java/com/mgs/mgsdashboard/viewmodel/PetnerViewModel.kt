package com.mgs.mgsdashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mgs.mgsdashboard.model.petnerApi.Petner
import com.mgs.mgsdashboard.service.PetnerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PetnerViewModel : ViewModel() {

    private val petnerApiService = PetnerService()
    private val disposable = CompositeDisposable()
    val getPetnerListLiveData = MutableLiveData<Petner>()

    fun refreshData() {
        getPetnerData()
    }

    fun cleanDisposable(){
        disposable.clear()
    }

    fun getPetnerData() {
        disposable.add(
            petnerApiService.getData()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Petner>() {
                    override fun onSuccess(t : Petner) {
                        getPetnerListLiveData.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}
