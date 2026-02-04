package com.aizarath.spool.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White

val Pantone = Color(0xFFFCE7B8)
val Phantom = Color(0xFF1C1B1B)
val EggWash = Color(0xFFF5DB86)
val DarkGreen = Color(0xFF0A3323)
val MossGreen = Color(0xFF839958)
val Beige = Color(0xFFF7F4D5)
val RosyBrown = Color(0xFFD3968C)
val MidnightGreen = Color(0xFF105666)

object DefaultTheme {
    val palette = listOf(
        DarkGreen, MossGreen, Beige, EggWash,
        RosyBrown, MidnightGreen, Phantom,
    )

    val defaultColor = RosyBrown

    fun getFontColor(backgroundColor: Color): Color{
        return when (backgroundColor) {
            DarkGreen -> MossGreen
            MossGreen -> MidnightGreen
            Beige -> Phantom
            EggWash -> DarkGreen
            RosyBrown -> Beige
            MidnightGreen -> EggWash
            Phantom -> RosyBrown
            else -> MossGreen
        }
    }
}