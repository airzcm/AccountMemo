package com.airzcm.accountmemo.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author airzcm on 2018/1/5.
 */
@Entity(tableName = "funds")
data class Funds(
        @PrimaryKey
        var id: Long,
        var date: String,
        var month: Int,
        var year: Int,
        var amount: Double,
        var type: Int,
        var purpose: String,
        var source: String,
        var comment: String
)