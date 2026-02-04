package com.aizarath.spool.feature_note.domain.use_case.folder

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFolder @Inject constructor(
    private val repository: FolderRepository
) {
    operator fun invoke(id: Int) : Flow<Folder?> {
        return repository.getFolderById(id)
    }
}