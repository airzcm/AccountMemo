package com.airzcm.accountmemo.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.airzcm.accountmemo.model.dao.FundsDao
import com.airzcm.accountmemo.model.entity.Funds

/**
 * @author airzcm on 2018/1/5.
 */
@Database(entities = [(Funds::class)], version = 1)
abstract class FundsDatabase private constructor() : RoomDatabase() {

    abstract fun fundsDao(): FundsDao

    //静态内部类
    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = FundsDatabase
    }

}