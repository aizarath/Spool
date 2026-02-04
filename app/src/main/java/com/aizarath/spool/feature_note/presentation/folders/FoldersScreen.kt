package com.aizarath.spool.feature_note.presentation.folders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.presentation.add_edit_folder.AddEditFolderDialog
import com.aizarath.spool.feature_note.presentation.folders.components.Folders
import com.aizarath.spool.feature_note.presentation.util.Screen

@Composable
fun FoldersScreen(
    navController: NavController,
    viewModel: FoldersViewModel = hiltViewModel()
) {
    val showAddFolderDialog = remember { mutableStateOf(false) }

    Folders(
        state = viewModel.state.value,
        onFolderClick = onFolderClick@{ folder ->
            val id = folder.id ?: return@onFolderClick
            navController.navigate(
                Screen.FolderNotesScreen.route + "?folderId=${id}&folderColor=${folder.color}"
            )
        },
        onAddFolderClick = { id, color ->
            showAddFolderDialog.value = true
        },
        onNoteClick = { folderId, noteId, color ->
            if (noteId != null && color != null){
                navController.navigate(
                    Screen.AddEditNoteScreen.route + "?folderId=${folderId}&noteId=${noteId}&noteColor=${color}"
                )
            } else {
                navController.navigate(Screen.AddEditNoteScreen.route)
            }
        }
    )

    if (showAddFolderDialog.value) {
        AddEditFolderDialog(
            folderId = null,
            folderColor = null,
            onDismiss = { showAddFolderDialog.value = false }
        )
    }
}