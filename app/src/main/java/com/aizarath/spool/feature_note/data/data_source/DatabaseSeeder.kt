package com.aizarath.spool.feature_note.data.data_source

import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aizarath.spool.R
import javax.inject.Inject

class DatabaseSeeder @Inject constructor() {
    fun seedDefaultFolder(db: SupportSQLiteDatabase){
        val now = System.currentTimeMillis()
        val values = ContentValues().apply{
            put("id", 1)
            put("name", "Vault X")
            put("description", "Default folder")
            put("timestamp", now)
            put("lastModified", now)
            put("color", 0xFFE1E3D3)
        }

        db.insert("Folder", OnConflictStrategy.IGNORE, values)
    }
}