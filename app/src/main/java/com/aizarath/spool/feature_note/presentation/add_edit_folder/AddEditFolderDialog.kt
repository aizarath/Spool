package com.aizarath.spool.feature_note.presentation.add_edit_folder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.aizarath.spool.feature_note.presentation.add_edit_folder.components.AddEditFolderCard
import com.aizarath.spool.feature_note.presentation.common.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditFolderDialog(
    folderId: Int?,
    folderColor: Int?,
    onDismiss: () -> Unit,
    viewModel: AddEditFolderViewModel = hiltViewModel(),
) {
    LaunchedEffect(viewModel.folderId) {
        viewModel.onEvent(
            AddEditFolderEvent.LoadFolder(
                folderId, folderColor
            )
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.NavigateBack -> onDismiss()
                else -> {}
            }
        }
    }


    Dialog(
        onDismissRequest = onDismiss
    ) {
        AddEditFolderCard(
            folderName = viewModel.folderName.value,
            folderDescription = viewModel.folderDescription.value,
            folderIcon = viewModel.folderIcon.value,
            folderColor = viewModel.folderColor.value,
            onDismiss = onDismiss,
            onEvent = { event -> viewModel.onEvent(event)},
            isSaveEnabled = viewModel.isSaveEnabled,
        )
    }
}