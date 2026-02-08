package com.aizarath.spool.feature_note.presentation.folder_notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.aizarath.spool.feature_note.presentation.add_edit_folder.AddEditFolderEvent

@Composable
fun FolderActions(
    onColorClick: () -> Unit
) {
    // Actions: Delete, Edit Details, Change Color
    Row() {
        IconButton(
            onClick = onColorClick
        ) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = "Change color",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit folder",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete folder",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}