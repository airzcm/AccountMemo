package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.airzcm.accountmemo.model.entity.Source

/**
 * @author airzcm on 2018/1/11.
 */
@Dao
interface SourceDao {

    @Insert
    fun insertEvent(source: Source)

    @Update
    fun updateEvent(source: Source)

    @Query("SELECT * from source")
    fun getAllEvent(): List<Source>

    @Query("SELECT * from source WHERE id = (:value)")
    fun getEvent(value: Int): List<Source>
}