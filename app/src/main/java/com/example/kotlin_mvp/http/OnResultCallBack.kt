package com.example.kotlin_mvp.http

interface OnResultCallBack<T> {

    fun onSuccess(t: T)

    fun onFail(e: Throwable)

}