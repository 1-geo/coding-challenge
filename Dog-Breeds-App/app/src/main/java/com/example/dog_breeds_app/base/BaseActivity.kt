package com.example.myapplication.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


/**
 * Base Activity
 */
abstract class BaseActivity<T: BasePresenter>: AppCompatActivity() {
    private lateinit var presenter: PresenterViewModel
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ViewModelProvider(this).get(PresenterViewModel::class.java)
        presenter.setPresenter(initPresenter())

        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
    }

    fun showProgress() {
        progressDialog.show()
    }

    fun cancelProgress() {
        progressDialog.cancel()
    }

    fun getPresenter(): T {
        return presenter.getPresenter()!! as T
    }

    abstract fun initPresenter(): T
}