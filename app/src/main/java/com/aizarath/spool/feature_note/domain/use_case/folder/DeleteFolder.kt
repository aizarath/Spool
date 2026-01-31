package com.aizarath.spool.feature_note.domain.use_case.folder

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import javax.inject.Inject

class DeleteFolder @Inject constructor(
    val repository: FolderRepository
) {
    suspend operator fun invoke(folder: Folder){
        repository.deleteFolder(folder)
    }
}