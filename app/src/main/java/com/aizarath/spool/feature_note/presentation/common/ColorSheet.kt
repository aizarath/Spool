package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.aizarath.spool.ui.theme.DefaultTheme

@Composable
fun ColorSheet(
    selectedColor: Int,
    onColorSelected: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = with(LocalDensity.current) { 80.dp } + 16.dp)
            .padding(bottom = 32.dp)
    ){
        Text(
            text = "Color",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DefaultTheme.palette.forEach  { color ->
                val colorInt = color.toArgb()
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .shadow(10.dp, CircleShape)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = 1.dp,
                            color = if (selectedColor == colorInt) MaterialTheme.colorScheme.outline else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable{onColorSelected(colorInt)}
                )
            }
        }
    }
}