package com.bibaswan.wipro_telstra.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "CanadaInfoList")
data class CanadaInfoList(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    @TypeConverters(InfoTypeConverter::class)
    val rows: List<CanadaInfo> = listOf()
)