package com.example.kotlin_mvp.base

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_mvp.base.presenter.MvpPresenter
import com.example.kotlin_mvp.base.view.MvpBaseView
import com.example.kotlin_mvp.base.view.MvpView

abstract class BaseAcitvity<V: MvpView, P: MvpPresenter<V>> : AppCompatActivity(), MvpBaseView {

    var TAG = ""
    lateinit var view: V
    lateinit var presenter: P
    lateinit var currentActivity: BaseAcitvity<V, P>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        view = createView()
        presenter = createPresenter()
        presenter.attachView(view)
        currentActivity = this
        initView()
    }

    abstract fun setLayout(): Int

    abstract fun createView(): V

    abstract fun createPresenter(): P

    abstract fun initView()

    override fun onResume() {
        super.onResume()
        currentActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        if(this.presenter != null){
            this.presenter.dettachView()
        }
        dismisProgressDialog()
    }

    override fun showProgressDialog() {
        Toast.makeText(currentActivity, "开始请求", Toast.LENGTH_SHORT).show()
    }

    override fun dismisProgressDialog() {
        Toast.makeText(currentActivity, "请求结束", Toast.LENGTH_SHORT).show()
    }
}