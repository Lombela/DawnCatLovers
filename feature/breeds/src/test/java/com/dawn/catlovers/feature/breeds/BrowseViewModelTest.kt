package com.dawn.catlovers.feature.breeds

import com.dawn.catlovers.core.domain.usecase.ObserveBreedsUseCase
import com.dawn.catlovers.core.domain.usecase.RefreshBreedsUseCase
import com.dawn.catlovers.core.domain.usecase.SetFavoriteUseCase
import com.dawn.catlovers.core.model.CoatLength
import com.dawn.catlovers.feature.breeds.uistate.QuickFilter
import com.dawn.catlovers.feature.breeds.viewmodel.BrowseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BrowseViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `starts refresh and exposes filtered breeds`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(
                testBreed(id = "beng", name = "Bengal", hypoallergenic = true),
                testBreed(
                    id = "mcoo",
                    name = "Maine Coon",
                    coatLength = CoatLength.Long,
                    hypoallergenic = false,
                ),
            ),
        )
        val viewModel = browseViewModel(repository)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }

        viewModel.selectFilter(QuickFilter.Hypoallergenic)
        advanceUntilIdle()

        assertEquals(1, repository.refreshCalls)
        assertEquals(listOf("beng"), viewModel.uiState.value.breeds.map { it.id })
        assertEquals(QuickFilter.Hypoallergenic, viewModel.uiState.value.selectedFilter)
        assertNull(viewModel.uiState.value.syncMessage)
    }

    @Test
    fun `refresh failure keeps saved data and exposes message`() = runTest {
        val repository = FakeCatBreedsRepository(
            breeds = listOf(testBreed()),
            refreshResult = Result.failure(IllegalStateException("offline")),
        )
        val viewModel = browseViewModel(repository)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.uiState.collect() }
        advanceUntilIdle()

        assertEquals(listOf("beng"), viewModel.uiState.value.breeds.map { it.id })
        assertEquals("Could not update breeds. Showing saved data when available.", viewModel.uiState.value.syncMessage)
    }

    @Test
    fun `toggle favorite delegates inverted value`() = runTest {
        val repository = FakeCatBreedsRepository(breeds = listOf(testBreed(id = "beng", isFavorite = false)))
        val viewModel = browseViewModel(repository)

        viewModel.toggleFavorite(testBreed(id = "beng", isFavorite = false))
        advanceUntilIdle()

        assertEquals("beng" to true, repository.favoriteUpdates.single())
    }

    private fun browseViewModel(repository: FakeCatBreedsRepository) = BrowseViewModel(
        observeBreeds = ObserveBreedsUseCase(repository),
        refreshBreeds = RefreshBreedsUseCase(repository),
        setFavorite = SetFavoriteUseCase(repository),
        dispatchers = mainDispatcherRule.testDispatchers(),
    )
}
