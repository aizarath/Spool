package com.aizarath.spool.feature_note.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aizarath.spool.R
import com.aizarath.spool.feature_note.domain.model.Folder
import java.io.File

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
                .padding(6.dp)
                .aspectRatio(1f)
        ){
            AsyncImage(
                model = File(folder.iconImage),
                contentDescription = "Folder Icon",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_vault),
                placeholder = painterResource(id = R.drawable.ic_vault),
            )
        }
        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(8.dp)
        ) {
            Text(
                text = folder.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.width(12.dp))
            folder.description?.let {
                Text(
                    text = it,
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