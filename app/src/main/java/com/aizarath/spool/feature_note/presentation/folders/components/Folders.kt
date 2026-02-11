package com.aizarath.spool.feature_note.presentation.folders.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.util.FolderOrder
import com.aizarath.spool.feature_note.domain.util.OrderType
import com.aizarath.spool.feature_note.presentation.common.AddFloatingButton
import com.aizarath.spool.feature_note.presentation.common.FolderItem
import com.aizarath.spool.feature_note.presentation.folders.FoldersState
import com.aizarath.spool.ui.theme.BeastlyFontFamily
import com.aizarath.spool.ui.theme.SpoolTheme

@Composable
fun Folders(
    state: FoldersState,
    onFolderClick: (folder: Folder) -> Unit,
    onAddFolderClick: (id: Int?, color: Int?) -> Unit,
    onNoteClick: (folderInt: Int?, noteId: Int?, color: Int?) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            AddFloatingButton(
                folderId = null,
                onNoteClick = onNoteClick
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box{
                    // Border
                    Text(
                        text = "Spool",
                        fontFamily = BeastlyFontFamily,
                        style = TextStyle(
                            fontSize = 40.sp,
                            drawStyle = Stroke(width = 4f, join = StrokeJoin.Round),
                            color = MaterialTheme.colorScheme.outline
                        )
                    )
                    // Fill (front)
                    Text(
                        text = "Spool",
                        fontFamily = BeastlyFontFamily,
                        style = TextStyle(
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.outline,
                                offset = Offset(0f, 12f),
                                blurRadius = 0f
                            )
                        )
                    )
                }
                IconButton(
                    onClick = {onAddFolderClick(null, null)}
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add folder", modifier = Modifier.size(54.dp), tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items( state.folders ){ folder ->
                    FolderItem(
                        folder = folder,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onFolderClick(folder)
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FoldersPreview(){
    SpoolTheme{
        Folders(
            state = FoldersState(
                folders = listOf(
                    Folder(
                        name = "Personal",
                        description = "Private notes and daily thoughts",
                        timestamp = System.currentTimeMillis(),
                        lastModified = System.currentTimeMillis(),
                        iconImage = "ic_folder_personal", // Example resource name
                        color = 0xFFBB86FC.toInt(), // Light Purple
                        id = 1
                    ),
                    Folder(
                        name = "Work Projects",
                        description = "Tasks, meeting minutes, and deadlines",
                        timestamp = System.currentTimeMillis() - 86400000, // 1 day ago
                        lastModified = System.currentTimeMillis(),
                        iconImage = "ic_folder_work",
                        color = 0xFF03DAC5.toInt(), // Teal
                        id = 2
                    ),
                    Folder(
                        name = "Travel Ideas",
                        description = "Bucket list and flight itineraries",
                        timestamp = System.currentTimeMillis() - 172800000, // 2 days ago
                        lastModified = System.currentTimeMillis() - 3600000, // 1 hour ago
                        iconImage = "ic_folder_travel",
                        color = 0xFFFFB74D.toInt(), // Orange
                        id = 3
                    ),
                    Folder(
                        name = "Finance",
                        description = "Expenses and budget planning",
                        timestamp = System.currentTimeMillis(),
                        lastModified = System.currentTimeMillis(),
                        iconImage = "ic_folder_finance",
                        color = 0xFF81C784.toInt(), // Green
                        id = 4
                    ),
                    Folder(
                        name = "Recipes",
                        description = null, // Testing a null description
                        timestamp = System.currentTimeMillis() - 604800000, // 1 week ago
                        lastModified = System.currentTimeMillis() - 86400000,
                        iconImage = "ic_folder_food",
                        color = 0xFFE57373.toInt(), // Red/Pink
                        id = 5
                    )
                ),
                folderOrder = FolderOrder.Modified(OrderType.Descending),
                isOrderSectionVisible = false,
            ),
            onFolderClick = { folder -> {} },
            onAddFolderClick = { id: Int?, color: Int? -> {} },
            onNoteClick = {folderId, noteId, color -> {}}
        )
    }
}