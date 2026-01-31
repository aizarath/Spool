package com.aizarath.spool.feature_note.domain.util

import com.aizarath.spool.feature_note.domain.model.Note

fun List<Note>.sortNotes(noteOrder: NoteOrder): List<Note>{
    return when(noteOrder.orderType){
        is OrderType.Ascending ->{
            when(noteOrder){
                is NoteOrder.Title -> sortedBy {it.title.lowercase()}
                is NoteOrder.Created -> sortedBy {it.timestamp}
                is NoteOrder.Modified -> sortedBy {it.lastModified}
                is NoteOrder.Color -> sortedBy {it.color}
            }
        }
        is OrderType.Descending ->{
            when(noteOrder){
                is NoteOrder.Title -> sortedByDescending {it.title.lowercase()}
                is NoteOrder.Created -> sortedByDescending {it.timestamp}
                is NoteOrder.Modified -> sortedByDescending {it.lastModified}
                is NoteOrder.Color -> sortedByDescending {it.color}
            }
        }
    }
}