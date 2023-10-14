package com.example.qrscanner3.db

import android.util.Log
import com.example.qrscanner3.model.BookModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseRealTime {
    private var TAG: String = "MyTag"
    private var databaseReference: DatabaseReference = Firebase.database.reference

    fun insertValueToRealTimeDB(bookName: String, bookAuthor: String, bookNumber: Int) {
        try {
            val bookInfo = BookModel(
                bookName = bookName,
                bookAuthor = bookAuthor,
            )
            databaseReference.child("Book-info").child(bookNumber.toString()).setValue(bookInfo)
        } catch (e: Exception) {
            Log.wtf(TAG, "An error occurred: ${e.message}")
        }
    }
}