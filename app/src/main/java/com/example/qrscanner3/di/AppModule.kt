package com.example.qrscanner3.di

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    @Named("Instance.QrCodeWriter")
    fun provideQrCodeWriter() = QRCodeWriter()

    @Singleton
    @Provides
    @Named("Instance.BarcodeEncoder")
    fun provideBarcodeEncoder() = BarcodeEncoder()

    @Singleton
    @Provides
    @Named("Instance.FirebaseRealTime")
    fun provideFirebaseRealTimeDB() = Firebase.database.reference
}