package com.aizarath.spool.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.use_case.note.NoteUseCases
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.domain.util.OrderType
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Modified(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType)
                {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.saveNoteAndTouchFolder(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is NotesEvent.ToggleSelection -> {
                val currentSelected = state.value.selectedNoteIds
                val newSelected = if (currentSelected.contains(event.noteId)){
                    currentSelected - event.noteId
                } else {
                    currentSelected + event.noteId
                }

                _state.value = state.value.copy(
                    selectedNoteIds = newSelected,
                    isSelectionMode = newSelected.isNotEmpty()
                )
            }
            is NotesEvent.SelectAll -> {
                val allIds = state.value.notes.map { it.id!! }.toSet()
                _state.value = state.value.copy(
                    selectedNoteIds = allIds
                )
            }
            is NotesEvent.ClearSelection -> {
                _state.value = state.value.copy(
                    selectedNoteIds = emptySet(),
                    isSelectionMode = false
                )
            }
            is NotesEvent.DeleteSelectedNotes -> {
                viewModelScope.launch {
                    noteUseCases.deleteNotes(state.value.selectedNoteIds.toList())

                    onEvent(NotesEvent.ClearSelection)
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach{notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}