package com.example.dog_breeds_app.ui.breedview

import com.example.a20180101_mm_nycschools.api.Api
import com.example.dog_breeds_app.base.BaseViewListener
import com.example.myapplication.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DogBreedInfoScreenPresenter: BasePresenter() {

    fun loadImageForBreed(name: String?, listener: ViewListener) {
        name?.let {
            compositeDisposable.add(
                Api.apiCall.retrieveBreedImages(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        listener.hideProgress()
                        listener.loadImages(it.message)
                    }, { error ->
                        listener.hideProgress()

                    })
            )
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    interface ViewListener: BaseViewListener {
        fun loadImages(imageUrls: List<String>)
        fun showErrorMessage(error: String)
    }
}