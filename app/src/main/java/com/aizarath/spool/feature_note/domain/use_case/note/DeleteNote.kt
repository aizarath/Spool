package com.aizarath.spool.feature_note.domain.use_case.note

import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}