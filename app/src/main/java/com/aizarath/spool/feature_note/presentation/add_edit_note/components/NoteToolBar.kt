package com.aizarath.spool.feature_note.presentation.add_edit_note.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NoteToolBar(
    onColorClick: () -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        IconButton(onClick = onColorClick) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = "Change color"
            )
        }
    }
}