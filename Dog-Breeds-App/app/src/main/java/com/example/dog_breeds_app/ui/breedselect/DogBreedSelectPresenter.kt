package com.example.dog_breeds_app.ui.breedselect

import android.util.Log
import com.example.a20180101_mm_nycschools.api.Api
import com.example.dog_breeds_app.base.BaseViewListener
import com.example.myapplication.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DogBreedSelectPresenter: BasePresenter() {

    fun getDogBreeds(listener: ViewListener) {
        listener.showProgress()

        compositeDisposable.add(
            Api.apiCall.retrieveBreeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.hideProgress()
                    listener.loadBreeds(it.breeds)
                }, { error ->
                    listener.showError(error.message ?: "")
                    listener.hideProgress()
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    interface ViewListener: BaseViewListener {
        fun loadBreeds(breeds: List<String>)
        fun showError(errorMessage: String)
    }
}