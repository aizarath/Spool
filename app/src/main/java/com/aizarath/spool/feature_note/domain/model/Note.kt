package com.aizarath.spool.feature_note.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.aizarath.spool.ui.theme.Blue40
import com.aizarath.spool.ui.theme.Green40
import com.aizarath.spool.ui.theme.Pink40
import com.aizarath.spool.ui.theme.Purple40
import com.aizarath.spool.ui.theme.PurpleGrey40
import com.aizarath.spool.ui.theme.Red40

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
){
    companion object {
        val noteColors = listOf(Green40, Blue40, Red40, Purple40, PurpleGrey40, Pink40)
    }
}
