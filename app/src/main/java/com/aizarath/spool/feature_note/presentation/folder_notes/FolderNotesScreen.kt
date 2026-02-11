package com.aizarath.spool.feature_note.presentation.folder_notes

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aizarath.spool.feature_note.presentation.add_edit_folder.AddEditFolderDialog
import com.aizarath.spool.feature_note.presentation.common.DeleteDialog
import com.aizarath.spool.feature_note.presentation.common.SelectionState
import com.aizarath.spool.feature_note.presentation.common.UiEvent
import com.aizarath.spool.feature_note.presentation.folder_notes.components.FolderNotes
import com.aizarath.spool.feature_note.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FolderNotesScreen(
    navController: NavController,
    viewModel: FolderNotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val onEvent = viewModel::onEvent
    val showAddFolderDialog = remember { mutableStateOf(false) }
    val showDeleteDialog = remember { mutableStateOf(false) }

    val selectionState = remember(state.selectedNoteIds, state.isSelectionMode) {
        SelectionState(
            isSelectionMode = state.isSelectionMode,
            selectedCount = state.selectedNoteIds.size,
            onClearSelection = { onEvent(FolderNotesEvent.ClearSelection) },
            onSelectAll = { onEvent(FolderNotesEvent.SelectAll) },
            onDelete = { showDeleteDialog.value = true }
        )
    }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UiEvent.NavigateBack -> {
                    navController.navigateUp()
                }
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    FolderNotes(
        state = state,
        onEvent = onEvent,
        snackbarHostState = snackbarHostState,
        onNoteClick = { folderId, noteId, color ->
            if (noteId != null && color != null) {
                navController.navigate(
                    Screen.AddEditNoteScreen.route + "?folderId=${folderId}&noteId=${noteId}&noteColor=${color}"
                )
            } else {
                navController.navigate(Screen.AddEditNoteScreen.route + "?folderId=${folderId}")
            }
        },
        onBackClick = { navController.navigateUp() },
        onEditClick = { showAddFolderDialog.value = true },
        selectionState = selectionState,
        onDeleteFolder = {onEvent(FolderNotesEvent.DeleteFolder)}
    )

    if (showAddFolderDialog.value) {
        AddEditFolderDialog(
            folderId = state.folder?.id,
            folderColor = state.folder?.color,
            onDismiss = { showAddFolderDialog.value = false }
        )
    }

    if (showDeleteDialog.value){
        DeleteDialog(
            count = selectionState.selectedCount,
            onDismiss = {showDeleteDialog.value = false},
            onDelete = {
                onEvent(FolderNotesEvent.DeleteSelectedNotes)
                showDeleteDialog.value = false
            }
        )
    }
}