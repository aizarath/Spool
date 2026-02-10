package com.aizarath.spool.feature_note.presentation.common

data class SelectionState(
    val isSelectionMode: Boolean,
    val selectedCount: Int,
    val onClearSelection: () -> Unit,
    val onSelectAll: () -> Unit,
    val onDelete: () -> Unit
)
