package com.example.myapplication.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Base Presenter
 */
abstract class BasePresenter {
    protected val compositeDisposable = CompositeDisposable()

    abstract fun onCleared()
}