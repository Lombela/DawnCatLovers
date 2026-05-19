package com.dawn.catlovers.feature.breeds

import com.dawn.catlovers.core.domain.usecase.ObserveBreedsUseCase
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.core.model.Lifestyle
import com.dawn.catlovers.feature.breeds.viewmodel.FiltersViewModel
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
class FiltersViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `initial state applies default filters and exposes origins from all breeds`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(
                testBreed(id = "beng", origin = "United States", hypoallergenic = true, energyLevel = 5),
                testBreed(
                    id = "mcoo",
                    name = "Maine Coon",
                    origin = "United States",
                    coatLength = CoatLength.Long,
                    hypoallergenic = false,
                    energyLevel = 3,
                ),
                testBreed(
                    id = "sibe",
                    name = "Siberian",
                    origin = "Russia",
                    coatLength = CoatLength.SemiLong,
                    hypoallergenic = true,
                    energyLevel = 4,
                ),
            ),
        )
        val viewModel = filtersViewModel(repository)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }
        advanceUntilIdle()

        assertEquals(listOf("beng"), viewModel.uiState.value.matches.map { it.id })
        assertEquals(listOf("Russia", "United States"), viewModel.uiState.value.origins)
    }

    @Test
    fun `filter actions update active filters`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(
                testBreed(
                    id = "indoor",
                    name = "Indoor",
                    indoor = true,
                    hypoallergenic = false,
                    energyLevel = 2,
                    coatLength = CoatLength.Long,
                ),
            ),
        )
        val viewModel = filtersViewModel(repository)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }

        viewModel.clearAll()
        viewModel.setCoatLength(CoatLength.Long)
        viewModel.setHypoallergenic(false)
        viewModel.setMinEnergy(null)
        viewModel.toggleLifestyle(Lifestyle.Indoor)
        advanceUntilIdle()

        assertEquals(listOf("indoor"), viewModel.uiState.value.matches.map { it.id })
        assertEquals(setOf(Lifestyle.Indoor), viewModel.uiState.value.filters.lifestyles)
        assertEquals(CoatLength.Long, viewModel.uiState.value.filters.coatLength)
    }

    private fun filtersViewModel(repository: FakeCatBreedsRepository) = FiltersViewModel(
        observeBreeds = ObserveBreedsUseCase(repository),
        dispatchers = mainDispatcherRule.testDispatchers(),
    )
}
