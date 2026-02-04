package com.aizarath.spool.feature_note.presentation.folder_notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
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

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UiEvent.NavigateBack -> {
                    navController.navigateUp()
                }
                is UiEvent.ShowSnackBar -> TODO()
            }
        }
    }

    FolderNotes(
        state = state,
        onEvent = viewModel::onEvent, // { viewModel.onEvent(it) }
        onNoteClick = { folderId, noteId, color ->
            if (noteId != null && color != null){
                navController.navigate(
                    Screen.AddEditNoteScreen.route + "?folderId=${folderId}&noteId=${noteId}&noteColor=${color}"
                )
            } else {
                navController.navigate(Screen.AddEditNoteScreen.route + "?folderId=${folderId}")
            }
        },
        onBackClick = {navController.navigateUp()}
    )
}