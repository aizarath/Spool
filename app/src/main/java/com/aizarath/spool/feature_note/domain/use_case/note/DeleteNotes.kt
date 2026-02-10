package com.aizarath.spool.feature_note.domain.use_case.note

import com.aizarath.spool.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNotes @Inject constructor(val repository: NoteRepository) {
    suspend operator fun invoke(ids: List<Int>){
        if (ids.isNotEmpty()){
            repository.deleteNotesByIds(ids)
        }
    }
}