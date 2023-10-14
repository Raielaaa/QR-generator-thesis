package com.example.qrscanner3.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.qrscanner3.db.FirebaseRealTime
import com.example.qrscanner3.db.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @Named("Instance.QrCodeWriter")
    private val writer: QRCodeWriter,
    @Named("Instance.BarcodeEncoder")
    private val barcodeEncoder: BarcodeEncoder
) : ViewModel() {

    fun generateQr(
        bookName: String,
        bookAuthor: String,
        bookNumber: Int
    ): Bitmap {
        val bitMatrix: BitMatrix = writer.encode(
            "BookName: $bookName : BookAuthor: $bookAuthor : BookNumber: $bookNumber",
            BarcodeFormat.QR_CODE,
            512,
            512
        )

        return barcodeEncoder.createBitmap(bitMatrix)
    }

    fun insertBookToRealTimeDB(bookName: String, bookAuthor: String, bookNumber: Int) {
        FirebaseRealTime.insertValueToRealTimeDB(
            bookName,
            bookAuthor,
            bookNumber
        )
    }

    fun uploadQrCode(bitmap: Bitmap, fileName: String) : UploadTask {
        return FirebaseStorage.uploadBitmap(bitmap, fileName)
    }
}