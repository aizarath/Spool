package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
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

    val slantedShape = SlantedShape(slantHeight = 80.dp)

    val baseColor = noteBackgroundAnimatable.value

    if (showColorSheet.value){
        ModalBottomSheet(
            onDismissRequest = {showColorSheet.value = false},
            sheetState = sheetState,
            shape = slantedShape,
            dragHandle = null,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = slantedShape
                    )
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
    }

    content(baseColor){
        showColorSheet.value = true
    }
}