package com.yudistiro.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yudistiro.domain.model.CurrentWeatherModel
import com.yudistiro.domain.model.DomainResource
import com.yudistiro.domain.model.ForeCastModel
import com.yudistiro.domain.usecase.GetCurrentWeatherUseCase
import com.yudistiro.domain.usecase.GetForecastByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var weatherUseCase: GetCurrentWeatherUseCase

    @MockK
    private lateinit var forecastUseCase: GetForecastByIdUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(weatherUseCase, forecastUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWeather should update weatherState with Success`() {

        val mockWeatherData = mockk<CurrentWeatherModel>()
        val successResource = DomainResource.Success(mockWeatherData)

        coEvery { weatherUseCase(any(), any()) } returns flowOf(successResource)

        val weatherObserver = mockk<Observer<DomainResource<CurrentWeatherModel>>>(relaxed = true)
        viewModel.weatherState.observeForever(weatherObserver)


        viewModel.fetchWeather(10.0, 20.0)
        testDispatcher.scheduler.advanceUntilIdle()


        verify { weatherObserver.onChanged(successResource) }
    }

    @Test
    fun `fetchWeather should update weatherState with Error`() {

        val errorResource = DomainResource.Error(
            Exception("Network Error"),
            "Failed to fetch weather"
        )

        coEvery { weatherUseCase(any(), any()) } returns flowOf(errorResource)

        val weatherObserver = mockk<Observer<DomainResource<CurrentWeatherModel>>>(relaxed = true)
        viewModel.weatherState.observeForever(weatherObserver)


        viewModel.fetchWeather(10.0, 20.0)
        testDispatcher.scheduler.advanceUntilIdle()


        verify {
            weatherObserver.onChanged(errorResource)
        }
    }

    @Test
    fun `getForecastById should update forecastState with Success`() {

        val mockForecastList = listOf(mockk<ForeCastModel>())
        val successResource = DomainResource.Success(mockForecastList)

        coEvery { forecastUseCase(any()) } returns flowOf(successResource)

        val forecastObserver = mockk<Observer<DomainResource<List<ForeCastModel>>>>(relaxed = true)
        viewModel.forecastState.observeForever(forecastObserver)


        viewModel.getForecastById(1)
        testDispatcher.scheduler.advanceUntilIdle()


        verify { forecastObserver.onChanged(successResource) }
    }

    @Test
    fun `getForecastById should update forecastState with SuccessNoData`() {

        val noDataResource = DomainResource.SuccessNoData("No forecast available")

        coEvery { forecastUseCase(any()) } returns flowOf(noDataResource)

        val forecastObserver = mockk<Observer<DomainResource<List<ForeCastModel>>>>(relaxed = true)
        viewModel.forecastState.observeForever(forecastObserver)


        viewModel.getForecastById(1)
        testDispatcher.scheduler.advanceUntilIdle()


        verify { forecastObserver.onChanged(noDataResource) }
    }

    @Test
    fun `initial weatherState should be Loading`() {

        assert(viewModel.weatherState.value is DomainResource.Loading)
    }

    @Test
    fun `initial forecastState should be Loading`() {

        assert(viewModel.forecastState.value is DomainResource.Loading)
    }
}