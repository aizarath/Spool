package com.aizarath.spool.feature_note.presentation.add_edit_folder

import android.net.Uri
import androidx.compose.ui.focus.FocusState
import com.aizarath.spool.feature_note.presentation.add_edit_note.AddEditNoteEvent

sealed class AddEditFolderEvent {
    data class EnteredName(val value: String): AddEditFolderEvent()
    data class ChangeNameFocus(val focusState: FocusState) : AddEditFolderEvent()
    data class EnteredDescription(val value: String): AddEditFolderEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddEditFolderEvent()
    data class ChangeIcon(val uri: Uri): AddEditFolderEvent()
    data class ChangeColor(val color: Int): AddEditFolderEvent()
    data class LoadFolder(val folderId: Int?, val defaultColor: Int?): AddEditFolderEvent()
    object Dismiss: AddEditFolderEvent()
    object SaveFolder: AddEditFolderEvent()
}