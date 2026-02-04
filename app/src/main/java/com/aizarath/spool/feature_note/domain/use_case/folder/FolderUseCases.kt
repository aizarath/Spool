package com.aizarath.spool.feature_note.domain.use_case.folder

import javax.inject.Inject

data class FolderUseCases @Inject constructor(
    val getFolders: GetFolders,
    val getFolder: GetFolder,
    val addFolder: AddFolder,
    val getFolderWithNotes: GetFolderWithNotes,
    val deleteFolder: DeleteFolder
)