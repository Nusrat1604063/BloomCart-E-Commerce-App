package com.freak.bloomcart.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module // define how to provide certain dep.
@InstallIn(SingletonComponent::class) // dep. are available
object AppModule {

    // Provide Firebase Firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}