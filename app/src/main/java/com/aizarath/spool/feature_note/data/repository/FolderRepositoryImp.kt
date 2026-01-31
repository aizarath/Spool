package com.aizarath.spool.feature_note.data.repository

import com.aizarath.spool.feature_note.data.data_source.FolderDao
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class FolderRepositoryImp(
    private val dao: FolderDao
) : FolderRepository {
    override fun getFolders(): Flow<List<Folder>> {
        return dao.getFolders()
    }

    override fun getFolderById(id: Int): Flow<Folder> {
        return dao.getFolderById(id)
    }

    override suspend fun upsertFolder(folder: Folder) {
        return dao.upsertFolder(folder)
    }

    override suspend fun deleteFolder(folder: Folder) {
        return dao.deleteFolder(folder)
    }
}