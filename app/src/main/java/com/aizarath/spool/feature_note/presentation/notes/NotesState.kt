package com.aizarath.spool.feature_note.presentation.notes

import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Created(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val selectedNoteIds: Set<Int> = emptySet(),
    val isSelectionMode: Boolean = false
)
