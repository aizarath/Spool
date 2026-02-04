package com.aizarath.spool.feature_note.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Folder::class,
            parentColumns = ["id"],
            childColumns = ["folderId"]
        )
    ],
    indices = [Index(value = ["folderId"])]
)
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val lastModified: Long,
    val color: Int,
    val background: String? = null,
    val folderId: Int,
    @PrimaryKey val id: Int? = null
)
