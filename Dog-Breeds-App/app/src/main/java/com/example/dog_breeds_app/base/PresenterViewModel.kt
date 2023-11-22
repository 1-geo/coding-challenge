package com.example.myapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresenterViewModel: ViewModel() {
    private var presenter: MutableLiveData<BasePresenter> = MutableLiveData()

    fun setPresenter(basePresenter: BasePresenter) {
        presenter.value = basePresenter
    }

    fun getPresenter(): BasePresenter? {
        return if (presenter.value != null) presenter.value!! else null
    }

    override fun onCleared() {
        super.onCleared()
        presenter.value?.onCleared()
    }
}