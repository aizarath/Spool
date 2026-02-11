package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.foundation.border
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aizarath.spool.ui.theme.SpoolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteDialog(
    count: Int = 1,
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.onError, RectangleShape,),
        title = {
            Text(
                text = "Confirm Delete",
                color = MaterialTheme.colorScheme.onError
            )
        },
        text = {
            Text(
                text = "Delete $count " +
                if (count > 1) {"notes?"} else {"note?"}
                + "\nThis action is undoable.",
                color = MaterialTheme.colorScheme.onError
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onDelete
            ) {
                Text(
                    text = "Delete",
                    color = Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancel",
                    color = Color.Black
                )
            }
        },
        shape = RectangleShape,
        containerColor = MaterialTheme.colorScheme.error
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DeleteDialogPreview(){
    SpoolTheme{
        DeleteDialog(
            count = 4,
            onDismiss = {},
            onDelete = {}
        )
    }
}