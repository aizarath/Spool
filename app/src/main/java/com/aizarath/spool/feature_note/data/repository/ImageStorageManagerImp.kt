package com.aizarath.spool.feature_note.data.repository

import android.content.Context
import android.net.Uri
import com.aizarath.spool.feature_note.domain.repository.ImageStorageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ImageStorageManagerImp @Inject constructor(
    @param: ApplicationContext private val context: Context
) : ImageStorageManager {
    override fun saveImage(uri: Uri): String? {
        val fileName = "icon_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)
        context.contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output -> input.copyTo(output) }
        }
        return file.absolutePath
    }

    override fun deleteImage(path: String): Boolean = File(path).delete()

}