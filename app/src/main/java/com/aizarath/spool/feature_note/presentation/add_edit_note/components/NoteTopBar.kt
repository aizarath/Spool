package com.aizarath.spool.feature_note.presentation.add_edit_note.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aizarath.spool.ui.theme.DefaultTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTopBar(
    onBackClick: () -> Unit,
    containerColor: Color,
    onContainerColor: Color,
) {
    TopAppBar(
        title = {Text("")},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            navigationIconContentColor = onContainerColor,
            actionIconContentColor = onContainerColor
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}