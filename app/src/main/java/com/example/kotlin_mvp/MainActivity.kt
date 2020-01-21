package com.example.kotlin_mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.kotlin_mvp.base.BaseAcitvity
import com.example.kotlin_mvp.base.view.MvpView
import com.example.kotlin_mvp.bean.ImagesBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAcitvity<MainView, MainPresenter>(), MainView {

    lateinit var bnGetData: Button
    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun createView(): MainView {
        return this
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun initView() {
        bnGetData = findViewById(R.id.bnGetData)
        tvResult = findViewById(R.id.tvResult)
        bnGetData.setOnClickListener {
            presenter?.getImages()
        }
    }

    override fun getImagesSuccess(t: ImagesBean) {
        tvResult.text = t.toString()
    }

    override fun getImagesFail() {
        tvResult.text = "网络请求错误 p.p"
    }
}
