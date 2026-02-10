package com.aizarath.spool.feature_note.presentation.folders

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.util.FolderOrder
import com.aizarath.spool.feature_note.domain.util.OrderType

data class FoldersState(
    val folders: List<Folder> = emptyList(),
    val folderOrder: FolderOrder = FolderOrder.Modified(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
)