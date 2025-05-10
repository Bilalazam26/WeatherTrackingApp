package com.bilalazzam.instabugweatherapp.domain.weather.usecases
import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.model.DailyForecast
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.io.IOException


class GetForecastWeatherUseCaseTest {
    private lateinit var fakeRepository: FakeWeatherRepository
    private lateinit var useCase: GetForecastWeatherUseCase

    fun setup() {
        fakeRepository = FakeWeatherRepository()
        useCase = GetForecastWeatherUseCase(fakeRepository)
    }

    @Test
    fun `execute should return success from repository`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)

        // When
        var result: Result<List<DailyForecast>>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertTrue(result!!.isSuccess)
    }

    @Test
    fun `execute should return the correct forecast data from repository`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)
        val expectedForecastData = listOf(DailyForecast("2023-05-10", 25.0, 31.0, "Sunny"))

        fakeRepository = FakeWeatherRepository(mockForecastData = expectedForecastData)
        useCase = GetForecastWeatherUseCase(fakeRepository)

        // When
        var result: Result<List<DailyForecast>>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertEquals(expectedForecastData, result!!.getOrNull())
    }

    @Test
    fun `execute should return failure from repository`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)
        val expectedException = IOException("Network error")

        fakeRepository = FakeWeatherRepository(shouldReturnError = true, mockException = expectedException)
        useCase = GetForecastWeatherUseCase(fakeRepository)

        // When
        var result: Result<List<DailyForecast>>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertTrue(result!!.isFailure)
    }

    @Test
    fun `execute should return the correct exception from repository failure`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)
        val expectedException = IOException("Network error")

        fakeRepository = FakeWeatherRepository(shouldReturnError = true, mockException = expectedException)
        useCase = GetForecastWeatherUseCase(fakeRepository)

        // When
        var result: Result<List<DailyForecast>>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertEquals(expectedException, result!!.exceptionOrNull())
    }
}