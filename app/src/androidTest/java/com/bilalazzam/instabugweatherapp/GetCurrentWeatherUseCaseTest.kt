package com.bilalazzam.instabugweatherapp.domain.weather.usecases

import com.bilalazzam.instabugweatherapp.domain.location.model.LocationCoordinate
import com.bilalazzam.instabugweatherapp.domain.weather.model.WeatherData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.io.IOException

class GetCurrentWeatherUseCaseTest {
    private lateinit var fakeRepository: FakeWeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    fun setup() {
        fakeRepository = FakeWeatherRepository()
        useCase = GetCurrentWeatherUseCase(fakeRepository)
    }

    @Test
    fun `execute should return success from repository`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)

        // When
        var result: Result<WeatherData>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertTrue(result!!.isSuccess)
    }

    @Test
    fun `execute should return the correct weather data from repository`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)
        val expectedWeatherData = WeatherData(25.0, "Sunny", 25.0, 50)

        fakeRepository = FakeWeatherRepository(mockWeatherData = expectedWeatherData)
        useCase = GetCurrentWeatherUseCase(fakeRepository)

        // When
        var result: Result<WeatherData>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertEquals(expectedWeatherData, result!!.getOrNull())
    }

    @Test
    fun `execute should return failure from repository`() {
        setup()
        // Given
        val location = LocationCoordinate(30.0, 31.0)
        val expectedException = IOException("Network error")

        fakeRepository = FakeWeatherRepository(shouldReturnError = true, mockException = expectedException)
        useCase = GetCurrentWeatherUseCase(fakeRepository)

        // When
        var result: Result<WeatherData>? = null
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
        useCase = GetCurrentWeatherUseCase(fakeRepository)

        // When
        var result: Result<WeatherData>? = null
        useCase.execute(location) {
            result = it
        }

        // Then
        assertEquals(expectedException, result!!.exceptionOrNull())
    }
}