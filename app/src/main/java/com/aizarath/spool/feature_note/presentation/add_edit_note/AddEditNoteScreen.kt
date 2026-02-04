package com.aizarath.spool.feature_note.presentation.add_edit_note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aizarath.spool.feature_note.presentation.common.UiEvent
import com.aizarath.spool.feature_note.presentation.add_edit_note.components.AddEditNote
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.NavigateBack -> {
                    navController.navigateUp()
                }
                else -> {}
            }
        }
    }

    AddEditNote(
        noteTitle = viewModel.noteTitle.value,
        noteContent = viewModel.noteContent.value,
        noteColor = viewModel.noteColor.value,
        onEvent = { viewModel.onEvent(it) },  // { viewModel.onEvent(it) }
    )
}