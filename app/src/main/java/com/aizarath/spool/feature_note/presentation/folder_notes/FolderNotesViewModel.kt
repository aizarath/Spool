package com.aizarath.spool.feature_note.presentation.folder_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.use_case.folder.FolderUseCases
import com.aizarath.spool.feature_note.domain.use_case.note.NoteUseCases
import com.aizarath.spool.feature_note.domain.util.NoteOrder
import com.aizarath.spool.feature_note.domain.util.OrderType
import com.aizarath.spool.feature_note.presentation.common.UiEvent
import com.aizarath.spool.feature_note.presentation.notes.NotesEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderNotesViewModel @Inject constructor(
    private val folderUseCases: FolderUseCases,
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(FolderNotesState())
    val state: State<FolderNotesState> = _state

    private val folderId: Int = checkNotNull(savedStateHandle["folderId"])

    private var getNotesJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getNotes(NoteOrder.Modified(OrderType.Descending))
    }

    fun onEvent(event: FolderNotesEvent){
        when(event){
            is FolderNotesEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType)
                {
                    return
                }
                getNotes(event.noteOrder)
            }
            is FolderNotesEvent.ChangeColor -> {
                val currentFolder = state.value.folder
                viewModelScope.launch {
                    currentFolder?.let {
                        folderUseCases.addFolder(
                            it.copy(color = event.color)
                        )
                    }
                }
            }
            is FolderNotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is FolderNotesEvent.ToggleSelection -> {
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
            is FolderNotesEvent.SelectAll -> {
                val allIds = state.value.notes.map {it.id!!}.toSet()
                _state.value = state.value.copy(selectedNoteIds = allIds)
            }
            is FolderNotesEvent.ClearSelection -> {
                _state.value = state.value.copy(selectedNoteIds = emptySet(), isSelectionMode = false)
            }
            is FolderNotesEvent.DeleteSelectedNotes -> {
                viewModelScope.launch {
                    noteUseCases.deleteNotes(state.value.selectedNoteIds.toList())

                    onEvent(FolderNotesEvent.ClearSelection)
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = folderUseCases.getFolderWithNotes(folderId, noteOrder)
            .onEach{ folderWithNotes ->
                if (folderWithNotes == null){
                    _eventFlow.emit(UiEvent.NavigateBack)
                    return@onEach
                }

                _state.value = state.value.copy(
                    folder = folderWithNotes.folder,
                    notes = folderWithNotes.notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}