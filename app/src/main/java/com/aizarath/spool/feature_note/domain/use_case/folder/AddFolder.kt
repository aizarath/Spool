package com.aizarath.spool.feature_note.domain.use_case.folder

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.model.InvalidFolderException
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class AddFolder @Inject constructor(
    private val repository : FolderRepository
) {

    @Throws(InvalidFolderException::class)
    suspend operator fun invoke(folder: Folder){
        if(folder.name.isBlank()){
            throw InvalidFolderException("Enter a name for the folder")
        }

        repository.upsertFolder(folder)
    }
}