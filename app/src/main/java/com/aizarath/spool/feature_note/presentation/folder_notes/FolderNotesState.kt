package com.aizarath.spool.feature_note.presentation.folder_notes

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.domain.util.OrderType

data class FolderNotesState(
    val folder: Folder? = null,
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Created(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
