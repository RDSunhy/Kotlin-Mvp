package com.example.kotlin_mvp.base.presenter

import com.example.kotlin_mvp.base.view.MvpView

interface MvpPresenter<V: MvpView> {

    fun attachView(view: V)

    fun dettachView()
}