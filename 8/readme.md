# Android Weather Stack App

A simple Android application built with Kotlin and Jetpack Compose that fetches real-time weather data using the OpenWeatherMap API and displays it in a scrollable list.

## Features

- **API Integration:** Uses Retrofit and Gson to fetch JSON data from OpenWeatherMap.
- **Concurrency:** Uses Kotlin Coroutines and ViewModelScope for asynchronous network calls.
- **UI:** Built entirely with Jetpack Compose using `LazyColumn` to stack weather results dynamically.
- **Architecture:** Follows MVVM (Model-View-ViewModel) pattern.

## Prerequisites

- Android Studio Hedgehog or later.
- OpenWeatherMap API Key.

## Setup

1. Clone the repository.
2. Open `MainActivity.kt`.
3. Locate the variable `private val apiKey` in the `WeatherViewModel` class.
4. Replace `"YOUR_OPENWEATHERMAP_API_KEY_HERE"` with your valid API key.
5. Sync Gradle and Run on Emulator/Device.

## Dependencies

- Retrofit 2.9.0
- Gson Converter 2.9.0
- Lifecycle ViewModel Compose 2.6.1
- Material3 Design
