package com.dawn.catlovers.feature.breeds

import androidx.lifecycle.SavedStateHandle
import com.dawn.catlovers.core.domain.usecase.ObserveBreedUseCase
import com.dawn.catlovers.core.domain.usecase.SetFavoriteUseCase
import com.dawn.catlovers.feature.breeds.viewmodel.DetailsViewModel
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
class DetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `observes breed from saved state id`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(testBreed(id = "sibe", name = "Siberian")),
        )
        val viewModel = detailsViewModel(repository, breedId = "sibe")
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }
        advanceUntilIdle()

        assertEquals("Siberian", viewModel.uiState.value.breed?.name)
    }

    @Test
    fun `toggle favorite delegates inverted value`() = runTest {
        val repository = FakeCatBreedsRepository(breeds = listOf(testBreed(id = "beng", isFavorite = true)))
        val viewModel = detailsViewModel(repository, breedId = "beng")

        viewModel.toggleFavorite(testBreed(id = "beng", isFavorite = true))
        advanceUntilIdle()

        assertEquals("beng" to false, repository.favoriteUpdates.single())
    }

    private fun detailsViewModel(
        repository: FakeCatBreedsRepository,
        breedId: String,
    ) = DetailsViewModel(
        savedStateHandle = SavedStateHandle(mapOf("breedId" to breedId)),
        observeBreed = ObserveBreedUseCase(repository),
        setFavorite = SetFavoriteUseCase(repository),
        dispatchers = mainDispatcherRule.testDispatchers(),
    )
}
