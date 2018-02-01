package com.airzcm.accountmemo.di.module

import android.app.Activity
import android.content.Context
import com.airzcm.accountmemo.di.ActivityScope
import dagger.Module
import dagger.Provides
import com.airzcm.accountmemo.di.ActivityContext

/**
 * @author airzcm on 2018/1/31.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }
}