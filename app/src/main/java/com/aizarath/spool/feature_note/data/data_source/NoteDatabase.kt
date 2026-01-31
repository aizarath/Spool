package com.aizarath.spool.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.model.Note

@Database(
    entities = [Note::class, Folder::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val folderDao: FolderDao

    companion object{
        const val DATABASE_NAME = "spool_db"
    }
}