package com.aizarath.spool.ui.theme

import androidx.compose.ui.graphics.Color

val Blush = Color(0xFFFFC4E2)
val Lemon = Color(0xFFFFF455)
val Tangerine = Color(0xFFF68A68)
val Maple = Color(0xFFDE75B8)
val Mango = Color(0xFFFFC700)
val Slate = Color(0xFF252526)
val CherryWine = Color(0xF4751427)

val Phantom = Color(0xFF0E0E0E)
val EggWash = Color(0xFFF5DB86)
val DarkGreen = Color(0xFF0A3323)
val MossGreen = Color(0xFF9FB771)
val Beige = Color(0xFFF7F4D5)
val RosyBrown = Color(0xFFD3968C)
val MidnightGreen = Color(0xFF105666)

object DefaultTheme {
    val palette = listOf(
        DarkGreen, MossGreen, Beige, EggWash,
        RosyBrown, MidnightGreen, Phantom,
    )

    val defaultColor = RosyBrown

    fun getContra(backgroundColor: Color): Color{
        return when (backgroundColor) {
            DarkGreen -> MossGreen
            MossGreen -> MidnightGreen
            Beige -> Phantom
            EggWash -> DarkGreen
            RosyBrown -> MidnightGreen
            MidnightGreen -> EggWash
            Phantom -> RosyBrown
            else -> Phantom
        }
    }
}