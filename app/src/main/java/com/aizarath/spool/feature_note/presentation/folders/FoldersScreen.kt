package com.aizarath.spool.feature_note.presentation.folders

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.presentation.folders.components.Folders
import com.aizarath.spool.feature_note.presentation.util.Screen

@Composable
fun FoldersScreen(
    navController: NavController,
    viewModel: FoldersViewModel = hiltViewModel()
) {
    Folders(
        state = viewModel.state.value,
        onFolderClick = onFolderClick@{ folder ->
            val id = folder.id ?: return@onFolderClick
            navController.navigate(
                Screen.FolderNotesScreen.route + "?folderId=${id}&folderColor=${folder.color}"
            )
        }
    )
}