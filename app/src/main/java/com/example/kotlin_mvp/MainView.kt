package com.example.kotlin_mvp

import com.example.kotlin_mvp.base.view.MvpBaseView
import com.example.kotlin_mvp.bean.ImagesBean

interface MainView : MvpBaseView{
    fun getImagesSuccess(t: ImagesBean)

    fun getImagesFail()
}