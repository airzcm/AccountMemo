package com.airzcm.accountmemo.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * @author airzcm on 2018/1/11.
 */
@Entity(tableName = "event",
        indices = [(Index("categoryId"))],
        foreignKeys = [(ForeignKey(entity = Category::class, parentColumns = arrayOf("id"), childColumns = arrayOf("categoryId")))])
data class Event(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var categoryId: Int,
        var event: String
)