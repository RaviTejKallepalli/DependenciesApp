package com.ravitej.dependenciesapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ravitej.dependenciesapp.MainCoroutineRule
import com.ravitej.dependenciesapp.data.local.CharacterDao
import com.ravitej.dependenciesapp.data.remote.CharacterRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class CharacterRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var remoteDataSource: CharacterRemoteDataSource

    @RelaxedMockK
    lateinit var localDataSource: CharacterDao

    private lateinit var classUnderTest: CharacterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }
}
