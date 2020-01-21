package com.example.kotlin_mvp.http

import io.reactivex.Observable

interface ApiServiceMethodCallBack<T> {
    fun createObservable (apiService: ApiService): Observable<T>
}