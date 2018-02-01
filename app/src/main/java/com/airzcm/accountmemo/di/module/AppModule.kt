package com.airzcm.accountmemo.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.airzcm.accountmemo.App
import com.airzcm.accountmemo.model.database.AccountDatabase
import dagger.Module
import dagger.Provides
import com.airzcm.accountmemo.di.ApplicationContext
import javax.inject.Singleton

/**
 * @author airzcm on 2018/1/30.
 */
@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApp(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideDatabase(): AccountDatabase {
        return Room.databaseBuilder(app,
                AccountDatabase::class.java, "Account.db")
                .allowMainThreadQueries()
                .build()
    }
}