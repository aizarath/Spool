package com.aizarath.spool.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteToolBar(
    onColorClick: () -> Unit,
    containerColor: Color,
    onContainerColor: Color
) {
    BottomAppBar(
        containerColor = containerColor,
        contentColor = onContainerColor,
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        IconButton(onClick = onColorClick) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = "Change color"
            )
        }
    }
}