package com.example.kotlin_mvp

import com.example.kotlin_mvp.base.model.MvpBaseModel
import com.example.kotlin_mvp.bean.ImagesBean
import com.example.kotlin_mvp.http.ApiService
import com.example.kotlin_mvp.http.ApiServiceMethodCallBack
import com.example.kotlin_mvp.http.HttpUtils
import io.reactivex.Observable
import io.reactivex.Observer

class MainModel : MvpBaseModel() {

    fun getImages(observer: Observer<ImagesBean>){
        HttpUtils.HttpRequest(observer, object : ApiServiceMethodCallBack<ImagesBean>{
            override fun createObservable(apiService: ApiService): Observable<ImagesBean> {
                return apiService.getImages()
            }
        })
    }
}