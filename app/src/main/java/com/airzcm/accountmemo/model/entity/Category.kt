package com.airzcm.accountmemo.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * @author airzcm on 2018/1/11.
 */
@Entity(tableName = "category")
//, foreignKeys = [(ForeignKey(entity = Event::class, parentColumns = arrayOf("categoryId"), childColumns = arrayOf("id")))])
data class Category(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var category: String
)