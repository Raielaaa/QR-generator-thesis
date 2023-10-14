package com.example.qrscanner3.db

import android.graphics.Bitmap
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream

object FirebaseStorage {
    private var storage = FirebaseStorage.getInstance().reference

    fun uploadBitmap(bitmap: Bitmap, fileName: String): UploadTask {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        return storage.child(fileName).putBytes(data)
    }
}