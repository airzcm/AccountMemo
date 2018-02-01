package com.airzcm.accountmemo.di.component

import com.airzcm.accountmemo.base.BaseActivity
import com.airzcm.accountmemo.di.ActivityScope
import com.airzcm.accountmemo.di.module.ActivityModule
import com.airzcm.accountmemo.view.createitem.CreateItemActivity
import com.airzcm.accountmemo.view.home.MainActivity
import dagger.Subcomponent

/**
 * @author airzcm on 2018/1/31.
 */
@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity): BaseActivity

    fun inject(mainActivity: MainActivity): MainActivity

    fun inject(createItemActivity: CreateItemActivity): CreateItemActivity

}