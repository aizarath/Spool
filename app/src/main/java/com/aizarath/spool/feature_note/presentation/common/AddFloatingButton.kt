package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun AddFloatingButton(
    folderId: Int?,
    onNoteClick: (folderId: Int?, noteId: Int?, color: Int?) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onNoteClick(folderId, null, null)
        },
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.outline, RectangleShape),
        shape = RectangleShape,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
    }
}