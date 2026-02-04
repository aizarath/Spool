package com.aizarath.spool.feature_note.presentation.add_edit_folder.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aizarath.spool.R
import com.aizarath.spool.feature_note.presentation.add_edit_folder.AddEditFolderEvent
import com.aizarath.spool.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import com.aizarath.spool.feature_note.presentation.common.TextFieldState
import com.aizarath.spool.ui.theme.DefaultTheme
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
        colors = CardDefaults.cardColors(containerColor = Color(folderColor)),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .padding(6.dp)
                    .aspectRatio(1f)
            ){
                AsyncImage(
                    model = folderIcon?.let { File(it) },
                    contentDescription = "Folder Icon",
                    modifier = Modifier.fillMaxSize(),
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
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = fontColor),
                    onFocusChange = {onEvent(AddEditFolderEvent.ChangeNameFocus(it))}
                )
                TransparentHintTextField(
                    text = folderDescription.text,
                    hint = folderDescription.hint,
                    isHintVisible = folderDescription.isHintVisible,
                    onValueChange = {onEvent(AddEditFolderEvent.EnteredDescription(it))},
                    fontColor = fontColor,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = fontColor),
                    onFocusChange = {onEvent(AddEditFolderEvent.ChangeDescriptionFocus(it))}
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.End
        ){
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
            Button(
                onClick = { onEvent(AddEditFolderEvent.SaveFolder) },
                enabled = isSaveEnabled
            ) {
                Text("Save")
            }
        }
    }
}