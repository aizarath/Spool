package com.aizarath.spool.feature_note.domain.use_case.note

import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.repository.NoteRepository
import com.aizarath.spool.feature_note.domain.util.DateFormatter
import javax.inject.Inject

class AddNote @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note): Long{
        // TODO(Discard note if title AND content are blank)
        return repository.upsertNote(note)
    }
}