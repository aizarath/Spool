package com.aizarath.spool.feature_note.domain.use_case.note

import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.repository.NoteRepository
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.domain.util.OrderType
import com.aizarath.spool.feature_note.domain.util.sortNotes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Modified(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { it.sortNotes(noteOrder) }
    }
}