package com.tvstreaming.italy.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class Channel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val streamUrl: String,
    val logoUrl: String = "",
    val category: String = "Generale",
    val isPreloaded: Boolean = false,
    val isFavorite: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)
