package com.ravitej.dependenciesapp.data.repository

import androidx.lifecycle.asFlow
import com.ravitej.dependenciesapp.data.local.CharacterDao
import com.ravitej.dependenciesapp.data.remote.CharacterRemoteDataSource
import com.ravitej.dependenciesapp.util.performGetOperation
import javax.inject.Inject

// Response<T>(Service) -> Resource<T>(DataSource) -> LiveData/Flow(Repository)
class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {

    suspend fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    ).asFlow()

    suspend fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    ).asFlow()
}
