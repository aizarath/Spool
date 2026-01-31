package com.aizarath.spool.feature_note.presentation.folder_notes

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aizarath.spool.feature_note.presentation.folder_notes.components.FolderNotes
import com.aizarath.spool.feature_note.presentation.util.Screen

@Composable
fun FolderNotesScreen(
    navController: NavController,
    viewModel: FolderNotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    FolderNotes(
        state = state,
        onEvent = viewModel::onEvent, // { viewModel.onEvent(it) }
        onNoteClick = { id, color ->
            if (id != null && color != null){
                navController.navigate(
                    Screen.AddEditNoteScreen.route + "?noteId=${id}&noteColor=${color}"
                )
            } else {
                navController.navigate(Screen.AddEditNoteScreen.route)
            }
        },
        onBackClick = {navController.navigateUp()}
    )
}