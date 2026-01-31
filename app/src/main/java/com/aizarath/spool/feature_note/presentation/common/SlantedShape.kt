package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class SlantedShape(val slantHeight: Dp = 40.dp): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val slantPx = with(density) {slantHeight.toPx()}
        val path = Path().apply{
            moveTo(0f, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, slantPx)
            lineTo( 0f, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}