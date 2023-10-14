package com.example.qrscanner3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.qrscanner3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init properties
        mainActivityViewModel = ViewModelProvider(this@MainActivity)[MainActivityViewModel::class.java]

        initClickFunctions()
    }

    private fun initClickFunctions() {
        binding.apply {
            btnGenerate.setOnClickListener {
                val bookName: String = etBookName.text.toString()
                val bookAuthor: String = etBookAuthor.text.toString()
                val bookNumber: Int = etBookNumber.text.toString().toInt()

                //  Generate QR code
                val bitmapImage = mainActivityViewModel.generateQr(bookName = bookName, bookAuthor = bookAuthor, bookNumber = bookNumber)
                ivQrCode.setImageBitmap(bitmapImage)

                //  Insert book data to Firebase Real-time DB
                mainActivityViewModel.insertBookToRealTimeDB(bookName = bookName, bookAuthor = bookAuthor, bookNumber = bookNumber)

                // Upload QR code to Firebase Storage
                mainActivityViewModel.uploadQrCode(bitmapImage, "qr_codes/$bookNumber.jpg")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@MainActivity, "Insert successful and QR code uploaded", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@MainActivity, "Error uploading QR code: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}