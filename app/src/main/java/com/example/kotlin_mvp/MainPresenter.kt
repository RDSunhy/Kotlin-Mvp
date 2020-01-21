package com.example.kotlin_mvp

import android.content.Context
import android.util.Log
import com.example.kotlin_mvp.base.presenter.MvpBasePresenter
import com.example.kotlin_mvp.base.presenter.MvpPresenter
import com.example.kotlin_mvp.bean.ImagesBean
import com.example.kotlin_mvp.http.HttpObserver
import com.example.kotlin_mvp.http.OnResultCallBack

class MainPresenter(context: Context) : MvpBasePresenter<MainView>(context) {

    var mainModel: MainModel = MainModel()

    fun getImages(){
        getView().showProgressDialog()
        mainModel.getImages(HttpObserver(object :  OnResultCallBack<ImagesBean> {
            override fun onSuccess(t: ImagesBean) {
                getView().getImagesSuccess(t)
                getView().dismisProgressDialog()
            }

            override fun onFail(e: Throwable) {
                getView().dismisProgressDialog()
            }

        }))
    }

}