# Weather Tracking App  
Android Internship Task 2025

## Overview
This is a clean and testable weather tracking app built in Kotlin using Jetpack Compose. It follows Clean Architecture and SOLID principles, and does not use any third-party libraries.

## Features

### Main Requirements
- Gets the user's current location using GPS
- Fetches current weather and 5-day forecast from the OpenWeatherMap API
- Includes two screens:
  - Current Weather Screen
  - 5-Day Forecast Screen
- Displays error messages when offline

### Optional and Bonus Features
- Caches the last retrieved data using custom JSON file-based caching
- Handles configuration changes like screen rotation using view-models
- Unit tests for domain use cases
- API key is secured using local.properties and Gradle build config
- Offline mode is supported using cached data

## Tech Stack (No Third-Party Libraries)
- Kotlin
- Jetpack Compose
- ViewModel
- State management
- HttpURLConnection for networking
- File-based JSON caching
- JUnit for unit testing

## Testing Strategy

- Unit Tests:
  - github action to run tests before pushes and pull-requests
  - Use cases: GetCurrentWeatherUseCase, GetForecastWeatherUseCase
  - Repository and data source logic
  - JSON parsing logic

Testing Principles Followed:
- One assert per test
- Given-When-Then structure
- Reusable fakes

## API Key Security

The API key is stored securely using local.properties and injected via Gradle:

local.properties
