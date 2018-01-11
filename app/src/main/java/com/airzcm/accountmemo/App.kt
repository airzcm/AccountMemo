package com.airzcm.accountmemo

import android.app.Application
import com.airzcm.accountmemo.model.database.AccountDatabase

/**
 * @author airzcm on 2018/1/11.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AccountDatabase.getInstance(this@App)
    }

    override fun onTerminate() {
        super.onTerminate()
        AccountDatabase.getInstance(this@App).close()
    }
}