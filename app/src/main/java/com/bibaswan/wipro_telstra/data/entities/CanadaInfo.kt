package com.bibaswan.wipro_telstra.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CanadaInfo")
data class CanadaInfo(
    @PrimaryKey
    val id: Int?,
    val imageHref: String?,
    val title: String?,
    val description: String?
)