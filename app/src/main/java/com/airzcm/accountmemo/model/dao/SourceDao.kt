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
    fun insertSource(source: Source)

    @Update
    fun updateSource(source: Source)

    @Query("SELECT * from Source")
    fun getAllSource(): List<Source>

    @Query("SELECT * from Source WHERE id = (:value)")
    fun getSource(value: Int): List<Source>
}