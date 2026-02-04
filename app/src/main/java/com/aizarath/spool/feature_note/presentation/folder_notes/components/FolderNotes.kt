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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aizarath.spool.feature_note.presentation.common.FolderItem
import com.aizarath.spool.feature_note.presentation.common.NoteItem
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesEvent
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderNotes(
    state: FolderNotesState,
    onEvent: (FolderNotesEvent) -> Unit,
    onNoteClick: (id: Int?, color: Int?) -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(
        topBar = {
            // Stick bar
            FolderTopBar(
                onBackClick = onBackClick,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNoteClick(null, null)
                },
                containerColor = Color.Red
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
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
                            onNoteClick(note.id, note.color)
                        },
                )
            }
        }
    }
}