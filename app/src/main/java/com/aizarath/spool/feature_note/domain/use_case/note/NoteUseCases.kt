package com.aizarath.spool.feature_note.domain.use_case.note

import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    val getNotes: GetNotes,
    val saveNoteAndTouchFolder: SaveNoteAndTouchFolder,
    val deleteNote: DeleteNote,
    val getNote: GetNote
)