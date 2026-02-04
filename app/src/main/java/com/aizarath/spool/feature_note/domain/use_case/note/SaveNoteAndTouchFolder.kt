package com.aizarath.spool.feature_note.domain.use_case.note

import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.use_case.folder.UpdateFolderTimestamp
import javax.inject.Inject

class SaveNoteAndTouchFolder @Inject constructor(
    private val addNote: AddNote,
    private val updateFolderTimestamp: UpdateFolderTimestamp
) {
    suspend operator fun invoke(note: Note): Long {
        val currentTime = System.currentTimeMillis()

        val noteId = addNote(note.copy(lastModified = currentTime))
        updateFolderTimestamp(note.folderId, currentTime)

        return noteId
    }
}