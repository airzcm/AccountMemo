package com.airzcm.accountmemo

import android.app.Application
import android.content.Context
import com.airzcm.accountmemo.di.component.AppComponent
import com.airzcm.accountmemo.di.component.DaggerAppComponent
import com.airzcm.accountmemo.di.module.AppModule
import com.facebook.stetho.Stetho

/**
 * @author airzcm on 2018/1/11.
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        //        lateinit var database: AccountDatabase
        operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

}