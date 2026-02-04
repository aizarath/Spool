package com.aizarath.spool.feature_note.presentation.add_edit_folder

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.use_case.folder.FolderUseCases
import com.aizarath.spool.feature_note.presentation.common.TextFieldState
import com.aizarath.spool.feature_note.presentation.common.UiEvent
import com.aizarath.spool.ui.theme.DefaultTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditFolderViewModel @Inject constructor(
    private val folderUseCases: FolderUseCases,
): ViewModel() {
    private val _folderName = mutableStateOf(TextFieldState(hint = "Name your folder"))
    val folderName: State<TextFieldState> = _folderName

    private val _folderDescription = mutableStateOf(TextFieldState(hint = "Describe your folder..."))
    val folderDescription: State<TextFieldState> = _folderDescription

    private val _folderColor = mutableIntStateOf(DefaultTheme.defaultColor.toArgb())
    val folderColor: State<Int> = _folderColor

    private val _folderIcon = mutableStateOf(String())
    val folderIcon: State<String?> = _folderIcon

    private val _folderId = mutableIntStateOf(-1)
    val folderId : State<Int> = _folderId

    private var folderTimestamp: Long = System.currentTimeMillis()

    private var currentFolderId: Int? = null

    private var lastSavedFolder: Folder? = null

    private var saveFolderJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow= _eventFlow.asSharedFlow()

    val isSaveEnabled: Boolean
        get() = _folderName.value.text.isNotBlank()

    fun onEvent(event: AddEditFolderEvent){
        when (event){
            is AddEditFolderEvent.EnteredName -> {
                _folderName.value = folderName.value.copy(
                    text = event.value
                )
            }
            is AddEditFolderEvent.ChangeNameFocus -> {
                _folderName.value = folderName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            folderName.value.text.isBlank()
                )
            }
            is AddEditFolderEvent.EnteredDescription -> {
                _folderDescription.value = folderDescription.value.copy(
                    text = event.value
                )
            }
            is AddEditFolderEvent.ChangeDescriptionFocus -> {
                _folderDescription.value = folderDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            folderDescription.value.text.isBlank()
                )
            }
            is AddEditFolderEvent.ChangeIcon -> {}
            is AddEditFolderEvent.ChangeColor -> {
                _folderColor.intValue = event.color
            }
            is AddEditFolderEvent.LoadFolder -> {
                if (event.defaultColor != null){
                    _folderColor.intValue = event.defaultColor
                }

                val id = event.folderId
                if (id != null){
                    _folderId.intValue = id
                    viewModelScope.launch {
                        getFolder(id)
                    }
                }
            }
            is AddEditFolderEvent.SaveFolder -> {
                saveFolderJob?.cancel()
                saveFolderJob = viewModelScope.launch {
                    saveFolder()
                    _eventFlow.emit(UiEvent.NavigateBack)
                }
            }
        }
    }

    private suspend fun getFolder(id: Int){
        folderUseCases.getFolder(id).collectLatest{ folder ->
            if (folder == null){
                return@collectLatest
            }
            currentFolderId = folder.id
            _folderName.value = folderName.value.copy(
                text = folder.name,
                isHintVisible = false
            )
            folder.description?.let{
                _folderDescription.value = folderDescription.value.copy(
                    text = folder.description,
                    isHintVisible = false
                )
            }
            _folderColor.intValue = folder.color
            folderTimestamp = folder.timestamp
        }
    }

    private suspend fun saveFolder(){
        val currentFolder = Folder(
            name = folderName.value.text,
            description = folderDescription.value.text,
            timestamp = folderTimestamp,
            lastModified = System.currentTimeMillis(),
            color = folderColor.value,
            iconImage = "",
            id = currentFolderId
        )

        // Compare the folders without considering the lastModified value
        if (currentFolder.copy(lastModified = 0) == lastSavedFolder?.copy(lastModified = 0)){
            return
        }

        val id = folderUseCases.addFolder(currentFolder)

        lastSavedFolder = currentFolder.copy(id = id.toInt())

        if (currentFolderId == null) {
            currentFolderId = id.toInt()
        }
    }
}