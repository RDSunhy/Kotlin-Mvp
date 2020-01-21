package com.example.kotlin_mvp.http

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.io.IOException

object HttpUtils {

    private var mRetrofit: Retrofit? = null
    private var mApiService: ApiService? = null

    init {

        /**
         * 初始化 okhttp
         */
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(AddHeaderInterceptor())
            .build()
        /**
         * 初始化 retrofit
         */
        mRetrofit = Retrofit.Builder()
            .baseUrl("http://gank.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        mApiService = mRetrofit!!.create<ApiService>(ApiService::class.java!!)
    }

    /**
     * 普通的网络请求注册
     * @param o
     * @param s
     * @param <T>
     */
    private fun <T> toSubscribe(o: Observable<T>, s: Observer<T>) {

        o.subscribeOn(Schedulers.io())
            .map { t -> t }
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s)
    }

    /**
     * 请求方法
     * @param <T>
     * @param subscriber 观察者
     * @param callBack   回调
     */
    fun <T> HttpRequest(subscriber: Observer<T>, callBack: ApiServiceMethodCallBack<T>) {
        try {
            toSubscribe(callBack.createObservable(mApiService!!), subscriber)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}