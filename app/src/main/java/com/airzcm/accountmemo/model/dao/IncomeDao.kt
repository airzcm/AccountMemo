package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.*
import com.airzcm.accountmemo.model.entity.Income

/**
 * @author airzcm on 2018/1/5.
 */
@Dao
interface IncomeDao {

    @Insert
    fun insertIncome(vararg income: Income)

    @Delete
    fun deleteIncome(income: Income)

    @Update
    fun updateIncome(income: Income)

    @Query("SELECT * from Income")
    fun getAllIncome(): List<Income>

    @Query("SELECT * from Income WHERE id = (:value)")
    fun getIncome(value: Int): List<Income>

    @Query("SELECT * from Income WHERE day = (:value)")
    fun getDayIncome(value: String): List<Income>

    @Query("SELECT * from Income WHERE month = (:value)")
    fun getMonthIncome(value: Int): List<Income>

    @Query("SELECT * from Income WHERE year = (:value)")
    fun getYearIncome(value: Int): List<Income>
}