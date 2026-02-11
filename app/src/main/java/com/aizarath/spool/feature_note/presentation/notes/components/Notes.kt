package com.aizarath.spool.feature_note.presentation.notes.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aizarath.spool.feature_note.presentation.common.AddFloatingButton
import com.aizarath.spool.feature_note.presentation.common.NoteItem
import com.aizarath.spool.feature_note.presentation.common.OrderSection
import com.aizarath.spool.feature_note.presentation.common.SelectionState
import com.aizarath.spool.feature_note.presentation.common.SelectionTopBar
import com.aizarath.spool.feature_note.presentation.notes.NotesEvent
import com.aizarath.spool.feature_note.presentation.notes.NotesState

@Composable
fun Notes(
    state: NotesState,
    onEvent: (NotesEvent) -> Unit,
    onNoteClick: (folderId: Int?, noteId: Int?, color: Int?) -> Unit,
    selectionState: SelectionState
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (state.isSelectionMode){
                SelectionTopBar(selectionState = selectionState)
            }
        },
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
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "X Folder",
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(
                    onClick = {
                        onEvent(NotesEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        onEvent(NotesEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.notes){ note ->
                    val isSelected = state.selectedNoteIds.contains(note.id)

                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth(),
                        isSelected = isSelected,
                        isSelectionMode = state.isSelectionMode,
                        onToggleSelection = { id ->
                            onEvent(NotesEvent.ToggleSelection(id))
                        },
                        onClick = {
                            onNoteClick(1, note.id, note.color)
                        },
                    )
                }
            }
        }
    }
}