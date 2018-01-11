package com.airzcm.accountmemo.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * @author airzcm on 2018/1/11.
 */
@Entity(tableName = "source")
//        , foreignKeys = [(ForeignKey(entity = Funds::class, parentColumns = arrayOf("sourceId"), childColumns = arrayOf("id")))])
data class Source(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var category: String,
        var event: String
)