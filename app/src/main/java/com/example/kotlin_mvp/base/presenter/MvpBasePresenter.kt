package com.example.kotlin_mvp.base.presenter

import android.content.Context
import com.example.kotlin_mvp.base.view.MvpView
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

open class MvpBasePresenter<V: MvpView> : MvpPresenter<V>{

    var weakContext: WeakReference<Context>
    lateinit var weakView: WeakReference<V>
    lateinit var proxyView: V

    constructor(context: Context){
        weakContext = WeakReference(context)
    }

    fun getContext(): Context? {
        return weakContext?.get()
    }

    fun getView(): V {
        return proxyView
    }

    fun isAttachView(): Boolean {
        if(weakView != null && weakView?.get() != null){
            return true
        }
        return false
    }

    override fun attachView(view: V) {
        weakView = WeakReference(view)
        var invocationHandler: MvpViewInvocationHandler? = MvpViewInvocationHandler(weakView?.get()!!)
        proxyView = Proxy.newProxyInstance(
            view.javaClass.classLoader,
            view.javaClass.interfaces,
            invocationHandler
        ) as V
    }

    override fun dettachView() {
        if(weakView != null){
            weakView?.clear()
        }
    }

    private inner class MvpViewInvocationHandler(
        private val mvpView: MvpView//要代理的对象
    ) : InvocationHandler {

        override fun invoke(proxy: Any?, method: Method?, arg: Array<Any>?): Any? {
            return if (isAttachView()) {
                method?.invoke(mvpView, *arg.orEmpty())
            } else null
        }

    }
}