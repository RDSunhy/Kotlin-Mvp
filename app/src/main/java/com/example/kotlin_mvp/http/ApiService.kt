package com.example.kotlin_mvp.http

import com.example.kotlin_mvp.bean.ImagesBean
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/api/data/%E7%A6%8F%E5%88%A9/10/1")
    fun getImages(): Observable<ImagesBean>
}