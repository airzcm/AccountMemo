package com.airzcm.accountmemo.base

/**
 * @author airzcm on 2018/1/17.
 */
class BasePresenter<V : BaseView> {

    var view: V? = null
        private set

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}