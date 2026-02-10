package com.aizarath.spool.feature_note.domain.repository

import com.aizarath.spool.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    fun getNotesByFolderId(folderId: Int): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun upsertNote(note: Note): Long
    suspend fun deleteNote(note: Note)
    suspend fun deleteNotesByIds(noteIds: List<Int>)
}