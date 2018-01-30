package com.airzcm.accountmemo.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * @author airzcm on 2018/1/17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        setSupportActionBar(toolbar)

        initView()
        initDatabase()
    }

    @LayoutRes
    abstract fun layout(): Int

    open fun initView() {

    }

    open fun initDatabase() {

    }

    override fun getContext(): Context {
        return this@BaseActivity
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(stringResId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(srtResId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}