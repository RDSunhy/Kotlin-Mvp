package com.example.kotlin_mvp.http

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HttpObserver<T> :Observer<T>{

    var onResultCallBack: OnResultCallBack<T>? = null
    lateinit var disposable: Disposable

    constructor(callBack: OnResultCallBack<T>){
        this.onResultCallBack = callBack
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        this.disposable = d
    }

    override fun onNext(t: T) {
        Log.e("请求成功",t.toString())
        onResultCallBack?.onSuccess(t)
    }

    override fun onError(e: Throwable) {
        Log.e("请求失败",e.toString())
        onResultCallBack?.onFail(e)
    }

    fun unSubscribe(){
        if (disposable != null && !disposable.isDisposed){
            disposable.dispose()
        }
    }

}