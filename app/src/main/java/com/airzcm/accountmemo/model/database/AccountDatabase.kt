package com.airzcm.accountmemo.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.airzcm.accountmemo.model.dao.EventDao
import com.airzcm.accountmemo.model.dao.FundsDao
import com.airzcm.accountmemo.model.dao.SourceDao
import com.airzcm.accountmemo.model.entity.Event
import com.airzcm.accountmemo.model.entity.Funds
import com.airzcm.accountmemo.model.entity.Source


/**
 * @author airzcm on 2018/1/5.
 */
@Database(entities = [(Funds::class), (Event::class), (Source::class)], version = 1)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun getFundsDao(): FundsDao
    abstract fun getEventDao(): EventDao
    abstract fun getSourceDao(): SourceDao

    //    单例
    companion object {

        @Volatile private var INSTANCE: AccountDatabase? = null

        fun getInstance(context: Context): AccountDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AccountDatabase::class.java, "Account.db")
                        .allowMainThreadQueries()
                        .build()

    }
}