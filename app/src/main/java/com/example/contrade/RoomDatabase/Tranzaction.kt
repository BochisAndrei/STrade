package com.example.contrade.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tranzactie_table")
class Tranzaction(
        @ColumnInfo(name = "name_company") val name: String,
        @ColumnInfo(name = "hour") val hour: String,
        @ColumnInfo(name = "day") val day: String,
        @ColumnInfo(name = "sum") val sum: Int,
        @ColumnInfo(name = "share_price") val share_price: Double,
        @ColumnInfo(name = "type") val type: String,
        @ColumnInfo(name = "ongoing") val ongoing: Boolean
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}