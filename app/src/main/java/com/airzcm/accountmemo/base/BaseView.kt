package com.airzcm.accountmemo.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * @author airzcm on 2018/1/17.
 */
interface BaseView {

    fun getContext(): Context

    fun showLoading()

    fun hideLoading()

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)
}