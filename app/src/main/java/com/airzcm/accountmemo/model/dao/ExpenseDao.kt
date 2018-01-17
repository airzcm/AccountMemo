package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.*
import com.airzcm.accountmemo.model.entity.Expense

/**
 * @author airzcm on 2018/1/5.
 */
@Dao
interface ExpenseDao {

    @Insert
    fun insertExpense(vararg expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Query("SELECT * from Expense")
    fun getAllExpense(): List<Expense>

    @Query("SELECT * from Expense WHERE id = (:value)")
    fun getExpense(value: Int): List<Expense>

    @Query("SELECT * from Expense WHERE day = (:value)")
    fun getDayExpense(value: String): List<Expense>

    @Query("SELECT * from Expense WHERE month = (:value)")
    fun getMonthExpense(value: Int): List<Expense>

    @Query("SELECT * from Expense WHERE year = (:value)")
    fun getYearExpense(value: Int): List<Expense>
}