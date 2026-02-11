package com.aizarath.spool.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aizarath.spool.R

val BeastlyFontFamily = FontFamily(
    Font(R.font.rubik_beastly, FontWeight.Normal)
)

val MazeFontFamily = FontFamily(
    Font(R.font.rubik_maze, FontWeight.Normal)
)

val ScribbleFontFamily = FontFamily(
    Font(R.font.rubik_scribble, FontWeight.Normal)
)

val BubblesFontFamily = FontFamily(
    Font(R.font.rubik_bubbles, FontWeight.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = BeastlyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 46.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

)