package com.aizarath.spool.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.aizarath.spool.feature_note.domain.model.Folder
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Query("SELECT * FROM Folder")
    fun getFolders(): Flow<List<Folder>>

    @Query("SELECT * FROM Folder WHERE id = :id")
    fun getFolderById(id: Int): Flow<Folder?>

    @Upsert
    suspend fun upsertFolder(folder: Folder) : Long

    @Query("UPDATE Folder Set lastModified = :timestamp WHERE id = :id")
    suspend fun updateFolderTimestamp(id: Int, timestamp: Long)


    @Delete
    suspend fun deleteFolder(folder: Folder)
}