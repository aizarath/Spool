package com.aizarath.spool.feature_note.domain.repository

import android.net.Uri


interface ImageStorageManager {
    fun saveImage(uri: Uri): String?
    fun deleteImage(path: String): Boolean
}