package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddFloatingButton(
    folderId: Int?,
    onNoteClick: (folderId: Int?, noteId: Int?, color: Int?) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onNoteClick(folderId, null, null)
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
    }
}