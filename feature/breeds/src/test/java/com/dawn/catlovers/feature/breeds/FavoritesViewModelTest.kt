package com.dawn.catlovers.feature.breeds

import com.dawn.catlovers.core.domain.usecase.ObserveBreedsUseCase
import com.dawn.catlovers.core.domain.usecase.SetFavoriteUseCase
import com.dawn.catlovers.feature.breeds.viewmodel.FavoritesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `exposes favorite breeds and suggested non-favorites`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(
                testBreed(id = "beng", name = "Bengal", isFavorite = true),
                testBreed(id = "sibe", name = "Siberian", isFavorite = false),
                testBreed(id = "bomb", name = "Bombay", isFavorite = true),
            ),
        )
        val viewModel = favoritesViewModel(repository)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }
        advanceUntilIdle()

        assertEquals(listOf("beng", "bomb"), viewModel.uiState.value.favoriteBreeds.map { it.id })
        assertEquals(listOf("sibe"), viewModel.uiState.value.suggestedBreeds.map { it.id })
        assertEquals("beng", viewModel.uiState.value.heroBreed?.id)
        assertEquals(listOf("bomb"), viewModel.uiState.value.alsoSavedBreeds.map { it.id })
    }

    @Test
    fun `toggle favorite delegates inverted value`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(testBreed(id = "beng", isFavorite = true)),
        )
        val viewModel = favoritesViewModel(repository)

        viewModel.toggleFavorite(testBreed(id = "beng", isFavorite = true))
        advanceUntilIdle()

        assertEquals("beng" to false, repository.favoriteUpdates.single())
    }

    private fun favoritesViewModel(repository: FakeCatBreedsRepository) = FavoritesViewModel(
        observeBreeds = ObserveBreedsUseCase(repository),
        setFavorite = SetFavoriteUseCase(repository),
        dispatchers = mainDispatcherRule.testDispatchers(),
    )
}
