package com.airzcm.accountmemo.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * @author airzcm on 2018/1/5.
 */
@Entity(indices = [Index("eventId")],
        foreignKeys = [(ForeignKey(entity = Event::class, parentColumns = arrayOf("id"), childColumns = arrayOf("eventId")))])
//,
//                (ForeignKey(entity = Source::class, parentColumns = arrayOf("id"), childColumns = arrayOf("sourceId")))])
data class Expense(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var amount: Double,
        var day: Int,
        var month: Int,
        var year: Int,
        var eventId: Int,
//        var sourceId: Int,
        var comment: String
)