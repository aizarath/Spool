package com.aizarath.spool.feature_note.domain.use_case.folder

import com.aizarath.spool.feature_note.domain.model.FolderWithNotes
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import com.aizarath.spool.feature_note.domain.repository.NoteRepository
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.domain.util.sortNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetFolderWithNotes @Inject constructor(
    private val folderRepository: FolderRepository,
    private val noteRepository: NoteRepository
) {
    operator fun invoke(
        folderId: Int,
        noteOrder: NoteOrder
    ): Flow<FolderWithNotes> {
        val folderFlow = folderRepository.getFolderById(folderId)
        val notesFlow = noteRepository.getNotesByFolderId(folderId)

        return combine(folderFlow, notesFlow) { folder, notes ->
            FolderWithNotes(
                folder = folder,
                notes = notes.sortNotes(noteOrder)
            )
        }
    }
}