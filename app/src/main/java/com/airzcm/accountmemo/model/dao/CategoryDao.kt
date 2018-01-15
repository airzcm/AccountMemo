package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.*
import com.airzcm.accountmemo.model.entity.Category
import com.airzcm.accountmemo.model.entity.Event

/**
 * @author airzcm on 2018/1/11.
 */
@Dao
interface CategoryDao {

    @Insert
    fun insertCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Query("SELECT * from category")
    fun getAllCategory(): List<Category>

    @Query("SELECT * from category WHERE id = (:value)")
    fun getCategory(value: Int): List<Category>
}