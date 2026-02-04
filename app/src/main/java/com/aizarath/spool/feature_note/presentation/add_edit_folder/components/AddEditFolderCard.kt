package com.aizarath.spool.feature_note.presentation.add_edit_folder.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aizarath.spool.R
import com.aizarath.spool.feature_note.presentation.add_edit_folder.AddEditFolderEvent
import com.aizarath.spool.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import com.aizarath.spool.feature_note.presentation.common.TextFieldState
import com.aizarath.spool.ui.theme.DefaultTheme
import com.aizarath.spool.ui.theme.SpoolTheme
import java.io.File

@Composable
fun AddEditFolderCard(
    folderName: TextFieldState,
    folderDescription: TextFieldState,
    folderIcon: String?,
    folderColor: Int,
    onDismiss: () -> Unit,
    onEvent: (AddEditFolderEvent) -> Unit,
    isSaveEnabled: Boolean = false
) {
    val fontColor = DefaultTheme.getFontColor(Color(folderColor))

    OutlinedCard(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .weight(0.35f)
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .border(1.dp, MaterialTheme.colorScheme.outline)
                ){
                    AsyncImage(
                        model = folderIcon?.let { File(it) },
                        contentDescription = "Folder Icon",
                        modifier = Modifier
                            .fillMaxSize(),
                        placeholder = painterResource(id = R.drawable.ic_vault),
                        error = painterResource(id = R.drawable.ic_vault),
                        contentScale = ContentScale.Crop,
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(0.65f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TransparentHintTextField(
                        text = folderName.text,
                        hint = folderName.hint,
                        isHintVisible = folderName.isHintVisible,
                        onValueChange = {onEvent(AddEditFolderEvent.EnteredName(it))},
                        fontColor = fontColor,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        onFocusChange = {onEvent(AddEditFolderEvent.ChangeNameFocus(it))}
                    )
                    TransparentHintTextField(
                        text = folderDescription.text,
                        hint = folderDescription.hint,
                        isHintVisible = folderDescription.isHintVisible,
                        onValueChange = {onEvent(AddEditFolderEvent.EnteredDescription(it))},
                        fontColor = fontColor,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        onFocusChange = {onEvent(AddEditFolderEvent.ChangeDescriptionFocus(it))}
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                TextButton(onClick = onDismiss) {
                    Text(
                        text = "Cancel",
                        color = Color.Black
                        )
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    onClick = { onEvent(AddEditFolderEvent.SaveFolder) },
                    enabled = isSaveEnabled,
                    shape = RectangleShape,
                    border = ButtonDefaults.outlinedButtonBorder().copy(
                        brush = SolidColor(MaterialTheme.colorScheme.outline)
                    )
                ) {
                    Text(
                        text = "Save",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditFolderCardPreview() {
    SpoolTheme {
        AddEditFolderCard(
            folderName = remember { TextFieldState("Work Project") },
            folderDescription = remember { TextFieldState("Important documents for the 2026 launch") },
            folderIcon = null,
            folderColor = Color.LightGray.toArgb(),
            onDismiss = {},
            onEvent = {},
            isSaveEnabled = true // Enabled because name is not blank
        )
    }
}