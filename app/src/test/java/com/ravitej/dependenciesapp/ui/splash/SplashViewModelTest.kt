package com.ravitej.dependenciesapp.ui.splash

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ravitej.dependenciesapp.MainCoroutineRule
import com.ravitej.dependenciesapp.data.entities.CharacterDetail
import com.ravitej.dependenciesapp.data.entities.CharacterList
import com.ravitej.dependenciesapp.data.entities.Info
import com.ravitej.dependenciesapp.data.repository.CharacterRepository
import com.ravitej.dependenciesapp.util.Resource
import com.ravitej.dependenciesapp.util.Success
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// https://github.com/ericdecanini/ShopshopShoppingList/blob/master/app/build.gradle
@ExperimentalCoroutinesApi
class SplashViewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var repository: CharacterRepository

    @RelaxedMockK
    lateinit var arguments: Bundle

    private lateinit var classUnderTest: SplashViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun test() = runBlocking {
        val expected: Resource<List<CharacterDetail>> = Resource.success(DummyObject.fakeListChar)
        coEvery { repository.getCharacters() }.returns(flow { emit(expected) })

        classUnderTest = SplashViewModel(repository, arguments)
        assertTrue(classUnderTest.testData.value.status is Success<*>)
    }
}

object DummyObject {
    val fakeListChar: List<CharacterDetail> = listOf(
        CharacterDetail(
            "20-01-2022",
            "male",
            1,
            "",
            "Dony Nuransyah",
            "man",
            "nothing",
            "unknown",
            ""
        )
    )
    val fakeCharacterList = CharacterList(Info(1, "", 1, false), fakeListChar)
    val fakeCharacter = CharacterDetail(
        "20-01-2022",
        "male",
        1,
        "",
        "Dony Nuransyah",
        "man",
        "nothing",
        "unknown",
        ""
    )
}
