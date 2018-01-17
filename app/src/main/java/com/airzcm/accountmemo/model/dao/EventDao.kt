package com.airzcm.accountmemo.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.airzcm.accountmemo.model.entity.Event

/**
 * @author airzcm on 2018/1/11.
 */
@Dao
interface EventDao {

    @Insert
    fun insertEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Query("SELECT * from Event")
    fun getAllEvent(): List<Event>

    @Query("SELECT * from Event WHERE categoryId = (:value)")
    fun getEvent(value: Int): List<Event>
}