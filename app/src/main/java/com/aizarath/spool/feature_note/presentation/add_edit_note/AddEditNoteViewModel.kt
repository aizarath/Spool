package com.aizarath.spool.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aizarath.spool.feature_note.presentation.common.UiEvent
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.domain.use_case.note.NoteUseCases
import com.aizarath.spool.feature_note.presentation.common.TextFieldState
import com.aizarath.spool.ui.theme.DefaultTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _noteTitle = mutableStateOf(
        TextFieldState(
            hint = "Enter title"
        )
    )
    val noteTitle: State<TextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(TextFieldState(
        hint = "Type something..."
    ))
    val noteContent: State<TextFieldState> = _noteContent

    private val _noteColor = mutableIntStateOf(savedStateHandle.get<Int>("noteColor") ?: DefaultTheme.defaultColor.toArgb())
    val noteColor: State<Int> = _noteColor

    private val _noteBackground = mutableStateOf<String?>(null)
    val noteBackground: State<String?> = _noteBackground

    private var noteTimestamp: Long = System.currentTimeMillis()

    private val _folderId = mutableIntStateOf(1)

    val folderId: State<Int> = _folderId

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    private var lastSavedNote: Note? = null

    private var saveNoteJob: Job? = null

    init {
        _folderId.intValue = savedStateHandle.get<Int>("folderId") ?: 1

        savedStateHandle.get<Int>("noteId")?.let{noteId ->
            if(noteId != -1){
                viewModelScope.launch{
                    noteUseCases.getNote(noteId)?.also{ note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.intValue = note.color
                        noteTimestamp = note.timestamp
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent){
        when (event){
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
                scheduleAutoSave()
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
                scheduleAutoSave()
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.intValue = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                saveNoteJob?.cancel()
                saveNoteJob = viewModelScope.launch {
                    saveNote()
                    _eventFlow.emit(UiEvent.NavigateBack)
                }
            }
        }
    }

    private suspend fun saveNote(){
        val currentNote = Note(
            title = noteTitle.value.text,
            content = noteContent.value.text,
            timestamp = noteTimestamp,
            lastModified = System.currentTimeMillis(),
            color = noteColor.value,
            background = noteBackground.value,
            folderId = folderId.value,
            id = currentNoteId
        )

        // Compare the notes without considering the lastModified value
        if (currentNote.copy(lastModified = 0) == lastSavedNote?.copy(lastModified = 0)){
            return
        }

        val id = noteUseCases.saveNoteAndTouchFolder(currentNote)

        lastSavedNote = currentNote.copy(id = id.toInt())

        if (currentNoteId == null){
            currentNoteId = id.toInt()
        }
    }

    private fun scheduleAutoSave(){
        saveNoteJob?.cancel()
        saveNoteJob = viewModelScope.launch {
            delay(500L)
            saveNote()
        }
    }
}