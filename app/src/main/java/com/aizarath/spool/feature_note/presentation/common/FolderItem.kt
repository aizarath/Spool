package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aizarath.spool.R
import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.ui.theme.BeastlyFontFamily
import com.aizarath.spool.ui.theme.BubblesFontFamily
import com.aizarath.spool.ui.theme.DefaultTheme

@Composable
fun FolderItem(
    folder: Folder,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(1.dp, Color.Black, RectangleShape)
            .background(Color(folder.color))
    ) {
        Box(
            modifier = Modifier
                .weight(0.35f)
                .padding(8.dp)
                .aspectRatio(1f)
                .border(1.dp, MaterialTheme.colorScheme.outline, RectangleShape)
        ){
            AsyncImage(
                model = folder.iconImage,
                contentDescription = "Folder Icon",
                modifier = Modifier
                    .fillMaxSize(),
                placeholder = painterResource(id = R.drawable.ic_vault),
                error = painterResource(id = R.drawable.ic_vault),
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(8.dp)
        ) {
            Box{
                // Border
                Text(
                    text = folder.name,
                    fontFamily = BubblesFontFamily,
                    style = TextStyle(
                        fontSize = 24.sp,
                        drawStyle = Stroke(width = 4f, join = StrokeJoin.Round),
                        color = MaterialTheme.colorScheme.outline
                    )
                )
                // Fill (front)
                Text(
                    text = folder.name,
                    fontFamily = BubblesFontFamily,
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            folder.description?.let {
                Text(
                    text = it,
                    color = DefaultTheme.getContra(Color(folder.color)),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun FolderItemPreview(){
    FolderItem(
        folder = Folder(
            name = "Vault X",
            description = "Default Folder",
            timestamp = System.currentTimeMillis(),
            lastModified = System.currentTimeMillis(),
            iconImage = "ic_vault.png",
            color = Color.LightGray.toArgb(),
            id = 1
        )
    )
}