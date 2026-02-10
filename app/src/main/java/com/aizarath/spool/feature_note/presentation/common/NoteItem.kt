package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.ui.theme.DefaultTheme

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onToggleSelection: (Int) -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(Color(note.color))
            .combinedClickable(
                onClick = {
                    if (isSelectionMode) onToggleSelection(note.id!!)
                    else onClick()
                },
                onLongClick = {
                    onToggleSelection(note.id!!)
                }
            )
            .border(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        if (isSelectionMode){
            Checkbox(
                checked = isSelected,
                onCheckedChange = {onToggleSelection(note.id!!)},
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(
                text = note.title,
                color = DefaultTheme.getContra(Color(note.color)),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                color = DefaultTheme.getContra(Color(note.color)),
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}