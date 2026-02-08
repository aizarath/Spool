package com.aizarath.spool.feature_note.presentation.folder_notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.model.Note
import com.aizarath.spool.feature_note.presentation.common.AddFloatingButton
import com.aizarath.spool.feature_note.presentation.common.ColorThemeWrapper
import com.aizarath.spool.feature_note.presentation.common.FolderItem
import com.aizarath.spool.feature_note.presentation.common.NoteItem
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesEvent
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesState
import com.aizarath.spool.ui.theme.DefaultTheme
import com.aizarath.spool.ui.theme.SpoolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderNotes(
    state: FolderNotesState,
    onEvent: (FolderNotesEvent) -> Unit,
    onNoteClick: (folderId: Int?, noteId: Int?, color: Int?) -> Unit,
    onBackClick: () -> Unit
) {

    ColorThemeWrapper(
        initialColor = state.folder?.color ?: DefaultTheme.defaultColor.toArgb(),
        onColorChanged = {newColor -> onEvent(FolderNotesEvent.ChangeColor(newColor))}
    ) { _, openSheet ->
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                // Sticky bar
                FolderTopBar(
                    onBackClick = onBackClick,
                    onColorClick = openSheet
                )
            },
            floatingActionButton = {
                AddFloatingButton(
                    folderId = state.folder?.id,
                    onNoteClick = onNoteClick
                )
            },
        ) { innerPadding ->
//        AnimatedVisibility(
//            visible = state.isOrderSectionVisible,
//            enter = fadeIn() + slideInVertically(),
//            exit = fadeOut() + slideOutVertically()
//        ) {
//            OrderSection(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 16.dp),
//                noteOrder = state.noteOrder,
//                onOrderChange = {
//                    onEvent(FolderNotesEvent.Order(it))
//                }
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = StaggeredGridItemSpan.FullLine){
                    state.folder?.let {
                        FolderItem(folder = it)
                    }
                }

                items(state.notes){ note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
                                onNoteClick(state.folder?.id,note.id, note.color)
                            },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FolderNotesPreview() {
    SpoolTheme {
        FolderNotes(
            state = FolderNotesState(
                folder = Folder(
                    name = "Random",
                    description = "Oh oh oh",
                    timestamp = System.currentTimeMillis(),
                    lastModified = System.currentTimeMillis(),
                    iconImage = "",
                    color = Color.LightGray.toArgb(),
                    id = 8
                ),
                notes = listOf(
                    Note(
                        id = 1,
                        title = "Grocery List",
                        content = "Milk, Eggs, Bread",
                        color = Color.Green.toArgb(),
                        timestamp = System.currentTimeMillis(),
                        lastModified = System.currentTimeMillis(),
                        folderId = 8
                    ),
                    Note(
                        id = 2,
                        title = "Gym Schedule",
                        content = "Leg day tomorrow",
                        color = Color.Yellow.toArgb(),
                        timestamp = System.currentTimeMillis(),
                        lastModified = System.currentTimeMillis(),
                        folderId = 8
                    )
                )
            ),
            onEvent = {},
            onNoteClick = {_, _, _ -> },
            onBackClick = {}
        )
    }
}