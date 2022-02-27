package com.ravitej.dependenciesapp.di

import android.content.Context
import com.ravitej.dependenciesapp.data.local.AppDatabase
import com.ravitej.dependenciesapp.data.local.CharacterDao
import com.ravitej.dependenciesapp.data.remote.CharacterRemoteDataSource
import com.ravitej.dependenciesapp.data.remote.CharacterService
import com.ravitej.dependenciesapp.data.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideRemoteCharacterDataSource(characterService: CharacterService) =
        CharacterRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideCharacterRepository(
        remoteDataSource: CharacterRemoteDataSource,
        localDatabase: CharacterDao
    ) = CharacterRepository(remoteDataSource, localDatabase)
}
