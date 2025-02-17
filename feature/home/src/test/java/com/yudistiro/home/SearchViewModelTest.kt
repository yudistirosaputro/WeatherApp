package com.yudistiro.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.LocationModel
import com.yudistiro.domain.usecase.SearchLocationWeatherUseCase
import com.yudistiro.domain.usecase.SaveLocationDataUseCase
import com.yudistiro.domain.usecase.GetSavedLocationUseCase
import com.yudistiro.domain.usecase.DeleteSavedLocationUseCase
import com.yudistiro.search.SearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*


@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var searchLocationWeatherUseCase: SearchLocationWeatherUseCase

    @MockK
    private lateinit var saveLocationDataUseCase: SaveLocationDataUseCase

    @MockK
    private lateinit var getSavedLocationUseCase: GetSavedLocationUseCase

    @MockK
    private lateinit var deleteSavedLocationUseCase: DeleteSavedLocationUseCase

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(
            searchLocationWeatherUseCase,
            saveLocationDataUseCase,
            getSavedLocationUseCase,
            deleteSavedLocationUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchLocations should update searchResults with Success`() {

        val mockLocations = listOf(
            mockk<LocationModel>(),
            mockk<LocationModel>()
        )
        val successResource = DomainResource.Success(mockLocations)

        coEvery { searchLocationWeatherUseCase(any()) } returns flowOf(successResource)

        val searchResultsList = mutableListOf<DomainResource<List<LocationModel>>>()
        viewModel.searchResults.observeForever { searchResultsList.add(it) }


        viewModel.searchLocations("New York")
        testDispatcher.scheduler.advanceUntilIdle()


        assertTrue(searchResultsList.last() is DomainResource.Success)
        assertEquals(mockLocations, (searchResultsList.last() as DomainResource.Success).data)
    }

    @Test
    fun `searchLocations should update searchResults with Error`() {

        val testException = Exception("Search failed")
        val errorResource = DomainResource.Error(
            testException,
            "Search error occurred"
        )

        coEvery { searchLocationWeatherUseCase(any()) } returns flowOf(errorResource)

        val searchResultsList = mutableListOf<DomainResource<List<LocationModel>>>()
        viewModel.searchResults.observeForever { searchResultsList.add(it) }


        viewModel.searchLocations("Invalid Location")
        testDispatcher.scheduler.advanceUntilIdle()


        assertTrue(searchResultsList.last() is DomainResource.Error)
        val errorState = searchResultsList.last() as DomainResource.Error
        assertEquals("Search error occurred", errorState.message)
        assertEquals(testException, errorState.error)
    }

    @Test
    fun `getSavedLocation should update savedLocations with Success`() {

        val mockSavedLocations = listOf(
            mockk<LocationModel>(),
            mockk<LocationModel>()
        )
        val successResource = DomainResource.Success(mockSavedLocations)

        coEvery { getSavedLocationUseCase() } returns flowOf(successResource)

        val savedLocationsList = mutableListOf<DomainResource<List<LocationModel>>>()
        viewModel.savedLocations.observeForever { savedLocationsList.add(it) }


        viewModel.getSavedLocation()
        testDispatcher.scheduler.advanceUntilIdle()


        assertTrue(savedLocationsList.last() is DomainResource.Success)
        assertEquals(mockSavedLocations, (savedLocationsList.last() as DomainResource.Success).data)
    }

    @Test
    fun `saveLocation should call saveLocationDataUseCase`() {

        val testLocation = mockk<LocationModel>()
        coEvery { saveLocationDataUseCase(any()) } returns Unit


        viewModel.saveLocation(testLocation)
        testDispatcher.scheduler.advanceUntilIdle()


        coVerify(exactly = 1) { saveLocationDataUseCase(testLocation) }
    }

    @Test
    fun `deleteLocation should call deleteSavedLocationUseCase`() {

        val testLocation = mockk<LocationModel>()
        coEvery { deleteSavedLocationUseCase(any()) } returns Unit


        viewModel.deleteLocation(testLocation)
        testDispatcher.scheduler.advanceUntilIdle()


        coVerify(exactly = 1) { deleteSavedLocationUseCase(testLocation) }
    }

    @Test
    fun `initial searchResults should be null`() {

        assertNull(viewModel.searchResults.value)
    }

    @Test
    fun `initial savedLocations should be null`() {

        assertNull(viewModel.savedLocations.value)
    }
}