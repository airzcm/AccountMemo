package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.*
import com.airzcm.accountmemo.model.entity.Funds

/**
 * @author airzcm on 2018/1/5.
 */
@Dao
interface FundsDao {

    @Insert
    fun insertFunds(funds: Funds)

    @Delete
    fun deleteFunds(funds: Funds)

    @Update
    fun updateFunds(funds: Funds)

    @Query("SELECT * from funds")
    fun getAllFunds(): List<Funds>

    @Query("SELECT * from funds WHERE date = (:value)")
    fun getDayFunds(value: String): List<Funds>

    @Query("SELECT * from funds WHERE month = (:value)")
    fun getMonthFunds(value: Int): List<Funds>

    @Query("SELECT * from funds WHERE year = (:value)")
    fun getYearFunds(value: Int): List<Funds>
}