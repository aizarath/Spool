package com.aizarath.spool.feature_note.presentation.add_edit_note.components

import android.graphics.Color.toArgb
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aizarath.spool.feature_note.presentation.common.SlantedShape
import com.aizarath.spool.feature_note.presentation.add_edit_note.AddEditNoteEvent
import com.aizarath.spool.feature_note.presentation.common.TextFieldState
import com.aizarath.spool.ui.theme.DefaultTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNote(
    noteTitle: TextFieldState,
    noteContent: TextFieldState,
    noteColor: Int,
    onEvent: (AddEditNoteEvent) -> Unit,
) {
    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if(noteColor != -1) noteColor else DefaultTheme.defaultColor.toArgb())
        )
    }

    val sheetState = rememberModalBottomSheetState()
    val showAppearanceSheet = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val fontColor = DefaultTheme.getFontColor(Color(noteColor))

    // TODO(Change this)
    var baseColor = Color(noteColor)
    val borderColor = lerp(baseColor, Color.Black, 0.5f)

    Scaffold(
        containerColor = Color(noteColor),
        topBar = {
            NoteTopBar(
                onBackClick = {onEvent(AddEditNoteEvent.SaveNote)}
            )
        },
        bottomBar = {
            NoteToolBar(
                onAppearanceClick = { showAppearanceSheet.value = true }
            )
        }
    ) { innerPadding ->

        if (showAppearanceSheet.value){
            ModalBottomSheet(
                onDismissRequest = {showAppearanceSheet.value = false},
                sheetState = sheetState,
                shape = SlantedShape(slantHeight = 80.dp),
                dragHandle = null,
                containerColor = borderColor
            ) {
                NoteAppearanceSheet(
                    selectedColor = noteColor,
                    onColorSelected = { colorInt ->
                        scope.launch{
                            noteBackgroundAnimatable.animateTo(
                                targetValue = Color(colorInt),
                                animationSpec = tween(durationMillis = 500)
                            )
                        }
                        onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                    }
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(noteBackgroundAnimatable.value)
                    .padding(16.dp)
            ){
                TransparentHintTextField(
                    text = noteTitle.text,
                    hint = noteTitle.hint,
                    onValueChange = {
                        onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                    onFocusChange ={
                        onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = noteTitle.isHintVisible,
                    singleLine = true,
                    fontColor = fontColor,
                    textStyle = MaterialTheme.typography.headlineMedium.copy(color = fontColor)
                )
                Spacer(modifier = Modifier.height(24.dp))
                TransparentHintTextField(
                    text = noteContent.text,
                    hint = noteContent.hint,
                    onValueChange = {
                        onEvent(AddEditNoteEvent.EnteredContent(it))
                    },
                    onFocusChange ={
                        onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = noteContent.isHintVisible,
                    fontColor = fontColor,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = fontColor),
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditNotePreview(){
    AddEditNote(
        noteTitle = TextFieldState(text = "Grocery List", hint = "Title", isHintVisible = false),
        noteContent = TextFieldState(text = "Milk, Eggs, Bread, and Coffee beans.", hint = "Type something", isHintVisible = false),
        noteColor = DefaultTheme.defaultColor.toArgb(),
        onEvent = {}
    )
}