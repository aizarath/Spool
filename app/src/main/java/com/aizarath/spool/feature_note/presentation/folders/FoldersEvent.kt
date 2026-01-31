package com.aizarath.spool.feature_note.presentation.folders

import com.aizarath.spool.feature_note.domain.util.FolderOrder

sealed class FoldersEvent {
    data class Order(val folderOrder: FolderOrder) : FoldersEvent()
    object ToggleOrderSection : FoldersEvent()
}