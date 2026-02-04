package com.aizarath.spool.feature_note.presentation.add_edit_folder

import androidx.compose.ui.focus.FocusState

sealed class AddEditFolderEvent {
    data class EnteredName(val value: String): AddEditFolderEvent()
    data class ChangeNameFocus(val focusState: FocusState) : AddEditFolderEvent()
    data class EnteredDescription(val value: String): AddEditFolderEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddEditFolderEvent()
    data class ChangeIcon(val iconImage: String?): AddEditFolderEvent()
    data class ChangeColor(val color: Int): AddEditFolderEvent()
    data class LoadFolder(val folderId: Int?, val defaultColor: Int?): AddEditFolderEvent()
    object SaveFolder: AddEditFolderEvent()
}