package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.aizarath.spool.feature_note.presentation.add_edit_note.AddEditNoteEvent
import com.aizarath.spool.ui.theme.DefaultTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorThemeWrapper(
    initialColor: Int,
    onColorChanged: (Int) -> Unit,
    content: @Composable (animatedColor: Color, openSheet: () -> Unit) -> Unit
) {
    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if(initialColor != -1) initialColor else DefaultTheme.defaultColor.toArgb())
        )
    }

    val sheetState = rememberModalBottomSheetState()
    val showColorSheet = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val baseColor = noteBackgroundAnimatable.value
    val darkerColor = lerp(baseColor, Color.Black, 0.5f)

    if (showColorSheet.value){
        ModalBottomSheet(
            onDismissRequest = {showColorSheet.value = false},
            sheetState = sheetState,
            shape = SlantedShape(slantHeight = 80.dp),
            dragHandle = null,
            containerColor = darkerColor
        ) {
            ColorSheet(
                selectedColor = initialColor,
                onColorSelected = { colorInt ->
                    scope.launch {
                        noteBackgroundAnimatable.animateTo(
                            targetValue = Color(colorInt),
                            animationSpec = tween(durationMillis = 500)
                        )
                    }
                    onColorChanged(colorInt)
                }
            )
        }
    }

    content(baseColor){
        showColorSheet.value = true
    }
}