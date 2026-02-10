package com.aizarath.spool.feature_note.presentation.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aizarath.spool.feature_note.presentation.common.DeleteDialog
import com.aizarath.spool.feature_note.presentation.common.SelectionState
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesEvent
import com.aizarath.spool.feature_note.presentation.notes.components.Notes
import com.aizarath.spool.feature_note.presentation.util.Screen

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val onEvent = viewModel::onEvent
    val showDeleteDialog = remember{ mutableStateOf(false) }

    val selectionState = remember(state.selectedNoteIds, state.isSelectionMode) {
        SelectionState(
            isSelectionMode = state.isSelectionMode,
            selectedCount = state.selectedNoteIds.size,
            onClearSelection = { onEvent(NotesEvent.ClearSelection) },
            onSelectAll = { onEvent(NotesEvent.SelectAll) },
            onDelete = { showDeleteDialog.value = true }
        )
    }

    Notes(
        state = state,
        onEvent = onEvent,
        onNoteClick = { folderId, noteId, color ->
            if (noteId != null && color != null){
                navController.navigate(
                    Screen.AddEditNoteScreen.route + "?folderId=${folderId}&noteId=${noteId}&noteColor=${color}"
                )
            } else {
                navController.navigate(Screen.AddEditNoteScreen.route)
            }
        },
        selectionState = selectionState
    )

    if (showDeleteDialog.value){
        DeleteDialog(
            count = selectionState.selectedCount,
            onDismiss = {showDeleteDialog.value = false},
            onDelete = {
                onEvent(NotesEvent.DeleteSelectedNotes)
                showDeleteDialog.value = false
            }
        )
    }
}