package com.airzcm.accountmemo.di.component

import com.airzcm.accountmemo.di.module.ActivityModule
import com.airzcm.accountmemo.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author airzcm on 2018/1/31.
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun plus(activityModule: ActivityModule): ActivityComponent

}