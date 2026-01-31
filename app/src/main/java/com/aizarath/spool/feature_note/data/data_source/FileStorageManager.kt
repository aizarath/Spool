package com.aizarath.spool.feature_note.data.data_source

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class FileStorageManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    fun saveDefaultPngToInternalStorage(resourceId: Int, fileName: String): String?{
        val file = File(context.filesDir, fileName)

        if(file.exists()) return fileName

        return try{
            context.resources.openRawResource(resourceId).use{ inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            fileName
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}