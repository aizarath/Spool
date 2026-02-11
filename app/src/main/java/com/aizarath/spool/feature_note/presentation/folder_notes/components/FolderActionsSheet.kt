package com.aizarath.spool.feature_note.presentation.folder_notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aizarath.spool.ui.theme.SpoolTheme

@Composable
fun FolderActionsSheet(
    onColorClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteFolder: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .padding(top = with(LocalDensity.current) { 80.dp } + 16.dp)
        .padding(bottom = 32.dp)
    ){
        Text(
            text = "Folder Menu",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{onColorClick()}
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Change Color",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{onEditClick()}
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Edit Folder",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{onDeleteFolder()}
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DeleteOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Delete Folder",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Preview
@Composable
fun FolderActionsSheetPreview(){
    SpoolTheme{
        FolderActionsSheet(
            onColorClick = {},
            onEditClick = {},
            onDeleteFolder = {}
        )
    }
}