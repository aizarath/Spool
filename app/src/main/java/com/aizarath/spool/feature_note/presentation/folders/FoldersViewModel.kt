package com.aizarath.spool.feature_note.presentation.folders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aizarath.spool.feature_note.domain.use_case.folder.FolderUseCases
import com.aizarath.spool.feature_note.domain.util.FolderOrder
import com.aizarath.spool.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoldersViewModel @Inject constructor(
    private val folderUseCases: FolderUseCases
) : ViewModel(){
    private val _state = mutableStateOf(FoldersState())
    val state: State<FoldersState> = _state

    private var getFoldersJob: Job? = null

    init {
        getFolders(FolderOrder.Modified(OrderType.Descending))
    }

    fun onEvent(event: FoldersEvent){
        when(event){
            is FoldersEvent.Order -> {
                getFolders(event.folderOrder)
            }
            is FoldersEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getFolders(folderOrder: FolderOrder){
        getFoldersJob?.cancel()
        getFoldersJob = folderUseCases.getFolders(folderOrder)
            .onEach { folders ->
                _state.value = state.value.copy(
                    folders = folders,
                    folderOrder = folderOrder
                )
            }
            .launchIn(viewModelScope)
    }
}