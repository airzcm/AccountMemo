package com.airzcm.accountmemo.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.airzcm.accountmemo.model.dao.*
import com.airzcm.accountmemo.model.entity.*


/**
 * @author airzcm on 2018/1/5.
 */
@Database(entities = [(Expense::class), (Income::class), (Event::class), (Category::class), (Source::class)], version = 1)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun getExpenseDao(): ExpenseDao
    abstract fun getIncomeDao(): IncomeDao
    abstract fun getEventDao(): EventDao
    abstract fun getCategoryDao(): CategoryDao
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