package com.mgs.mgsdashboard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mgs.mgsdashboard.model.redmine.Redmine
import com.mgs.mgsdashboard.service.RedmineService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RedmineViewModel : ViewModel() {

    private val redmineApiService = RedmineService()
    private val disposable = CompositeDisposable()
    val getRedmineListLiveData = MutableLiveData<Redmine>()

    fun refreshData() {
        getRedmineData()
    }

    fun cleanDisposable(){
        disposable.clear()
    }

    fun getRedmineData() {
        disposable.add(
            redmineApiService.getData()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Redmine>() {
                    override fun onSuccess(t : Redmine) {
                        getRedmineListLiveData.value = t
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}