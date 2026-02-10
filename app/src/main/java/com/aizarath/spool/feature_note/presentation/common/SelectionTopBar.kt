package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionTopBar(
    selectionState: SelectionState,
) {
    TopAppBar(
        title = { Text("${selectionState.selectedCount} selected") },
        navigationIcon = {
            IconButton(onClick = selectionState.onClearSelection) {
                Icon(Icons.Default.Close, contentDescription = "Cancel")
            }
        },
        actions = {
            IconButton(onClick = selectionState.onSelectAll) {
                Icon(Icons.Default.SelectAll, contentDescription = "Select All")
            }
            IconButton(onClick = selectionState.onDelete) {
                Icon(Icons.Default.DeleteOutline, contentDescription = "Delete")
            }
        }
    )
}