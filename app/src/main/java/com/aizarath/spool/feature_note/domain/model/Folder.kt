package com.aizarath.spool.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folder(
    val name: String,
    val description: String?,
    val timestamp: Long,
    val lastModified: Long,
    val iconImage: String?,
    val color: Int,
    @PrimaryKey val id: Int? = null
)