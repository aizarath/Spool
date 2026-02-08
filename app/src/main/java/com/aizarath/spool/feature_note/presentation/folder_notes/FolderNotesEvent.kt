package com.aizarath.spool.feature_note.presentation.folder_notes

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.presentation.notes.NotesEvent

sealed class FolderNotesEvent {
    data class Order(val noteOrder: NoteOrder): FolderNotesEvent()
    data class DeleteNote(val note: Note): FolderNotesEvent()
    data class DeleteFolder(val folder: Folder): FolderNotesEvent()
    data class ChangeColor(val color: Int) : FolderNotesEvent()
    object RestoreNote: FolderNotesEvent()
    object ToggleOrderSection: FolderNotesEvent()
}