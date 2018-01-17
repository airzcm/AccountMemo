package com.airzcm.accountmemo.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
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
}