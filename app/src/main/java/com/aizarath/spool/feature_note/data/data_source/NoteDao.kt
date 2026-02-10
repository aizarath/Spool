package com.aizarath.spool.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.aizarath.spool.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao{
    @Query("SELECT * FROM Note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE folderId = :folderId")
    fun getNotesByFolderId(folderId: Int): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Upsert
    suspend fun upsertNote(note: Note): Long

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM Note WHERE id IN (:noteIds)")
    suspend fun deleteNotesByIds(noteIds: List<Int>)
}