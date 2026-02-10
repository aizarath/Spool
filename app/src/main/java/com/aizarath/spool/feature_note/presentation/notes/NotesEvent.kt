package com.aizarath.spool.feature_note.presentation.notes

import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesEvent

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
    data class ToggleSelection(val noteId: Int) : NotesEvent()
    object SelectAll : NotesEvent()
    object ClearSelection : NotesEvent()
    object DeleteSelectedNotes : NotesEvent()
}