package com.aizarath.spool.feature_note.presentation.util

sealed class Screen(
    val route: String
) {
    object FoldersScreen: Screen("folders_screen")
    object FolderNotesScreen: Screen("folder_notes_screen")
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}