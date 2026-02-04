package com.aizarath.spool.feature_note.domain.use_case.folder

import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import javax.inject.Inject

class UpdateFolderTimestamp @Inject constructor(
    private val folderRepository: FolderRepository
) {
    suspend operator fun invoke(id: Int, timestamp: Long){
        folderRepository.updateFolderTimestamp(id, timestamp)
    }
}