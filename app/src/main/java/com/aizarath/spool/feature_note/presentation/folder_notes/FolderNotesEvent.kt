package com.aizarath.spool.feature_note.presentation.folder_notes

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.util.NoteOrder

sealed class FolderNotesEvent {
    data class Order(val noteOrder: NoteOrder): FolderNotesEvent()
    data class ChangeColor(val color: Int) : FolderNotesEvent()
    object ToggleOrderSection: FolderNotesEvent()
    object DeleteFolder: FolderNotesEvent()
    data class ToggleSelection(val noteId: Int) : FolderNotesEvent()
    object SelectAll : FolderNotesEvent()
    object ClearSelection : FolderNotesEvent()
    object DeleteSelectedNotes : FolderNotesEvent()
}