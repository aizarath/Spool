package com.aizarath.spool.feature_note.domain.model

data class FolderWithNotes(
    val folder: Folder,
    val notes: List<Note>
)
