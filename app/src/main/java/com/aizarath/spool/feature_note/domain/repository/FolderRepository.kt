package com.aizarath.spool.feature_note.domain.repository

import com.aizarath.spool.feature_note.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    fun getFolders(): Flow<List<Folder>>

    fun getFolderById(id: Int): Flow<Folder>

    suspend fun upsertFolder(folder: Folder)

    suspend fun deleteFolder(folder: Folder)
}