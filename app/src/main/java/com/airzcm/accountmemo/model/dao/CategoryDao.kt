package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.airzcm.accountmemo.model.entity.Category

/**
 * @author airzcm on 2018/1/11.
 */
@Dao
interface CategoryDao {

    @Insert
    fun insertCategory(vararg category: Category)

    @Update
    fun updateCategory(category: Category)

    @Query("SELECT * from Category")
    fun getAllCategory(): List<Category>

    @Query("SELECT * from Category WHERE id = (:value)")
    fun getCategory(value: Int): List<Category>
}