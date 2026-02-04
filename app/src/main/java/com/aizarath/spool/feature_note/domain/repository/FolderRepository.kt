package com.aizarath.spool.feature_note.domain.repository

import com.aizarath.spool.feature_note.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    fun getFolders(): Flow<List<Folder>>

    fun getFolderById(id: Int): Flow<Folder?>

    suspend fun upsertFolder(folder: Folder): Long

    suspend fun updateFolderTimestamp(id: Int, timestamp: Long)

    suspend fun deleteFolder(folder: Folder)
}