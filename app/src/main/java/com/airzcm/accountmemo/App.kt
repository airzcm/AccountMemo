package com.airzcm.accountmemo

import android.app.Application
import android.arch.persistence.room.Room
import com.airzcm.accountmemo.model.database.AccountDatabase
import com.facebook.stetho.Stetho

/**
 * @author airzcm on 2018/1/11.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        database = Room.databaseBuilder(applicationContext,
                AccountDatabase::class.java, "Account.db")
                .allowMainThreadQueries()
                .build()
    }

    override fun onTerminate() {
        super.onTerminate()
        database.close()
    }

    companion object {
        lateinit var database: AccountDatabase
    }

}