<h1>🌀 POKEMON 🌀</h1>
<img src="https://github.com/aarush368/pokemon_android_application/blob/master/app/img/darkmode_detail_screen.jpg" alt="pokemon" width="200" height="400" style="display:inline-block;">
<img src="https://github.com/aarush368/pokemon_android_application/blob/master/app/img/darmode_list_screen.jpg" alt="pokemon" width="200" height="400" style="display:inline-block;">
<img src="https://github.com/aarush368/pokemon_android_application/blob/master/app/img/light_mode_detail_screen.jpg" alt="pokemon" width="200" height="400" style="display:inline-block;">
<img src="https://github.com/aarush368/pokemon_android_application/blob/master/app/img/light_mode_list_screen.jpg" alt="pokemon" width="200" height="400" style="display:inline-block;">
<img src="https://github.com/aarush368/pokemon_android_application/blob/master/app/img/splash_screen.jpg" alt="pokemon" width="200" height="400" style="display:inline-block;">
# Pokémon Android Application

This is a Pokémon Android application built using Kotlin, Jetpack Compose, Hilt for dependency injection, and the MVVM (Model-View-ViewModel) pattern. The application fetches a list of Pokémon from a server, and upon clicking an item, navigates to a details page to show more information about the selected Pokémon.

## Features

- Fetch Pokémon list from the server.
- Display Pokémon list using Jetpack Compose.
- Navigate to a details page on item click.
- Show detailed information of the selected Pokémon.

## Architecture

The application follows the MVVM architecture pattern:

- **Model:** Represents the data and business logic of the app. In this app, it includes data classes and repository classes for fetching Pokémon data from the server.
- **View:** Composes UI elements using Jetpack Compose.
- **ViewModel:** Acts as a bridge between the Model and the View. It holds and manages UI-related data in a lifecycle-conscious way.

## Dependencies

- **Kotlin**: A modern programming language that makes developers happier.
- **Jetpack Compose**: Android’s modern toolkit for building native UI.
- **Hilt**: A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection.
- **Retrofit**: A type-safe HTTP client for Android and Java.
- **Coroutines**: For managing background threads and simplifying code to run asynchronously.

## Getting Started

### Prerequisites

- Android Studio Bumblebee (or later)
- Gradle 7.0 (or later)
- Internet connection (to fetch Pokémon data)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/pokemon-app.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

### Configuration

The project uses Retrofit to fetch data from the server. Make sure you have the correct base URL in your `ApiService` class.

## Usage

1. Launch the app on an Android device or emulator.
2. The main screen will display a list of Pokémon fetched from the server.
3. Click on any Pokémon item to navigate to the details page and view more information.

## Code Overview

## Download App. 
1. [Download Pokémon App][(https://drive.google.com/file/d/1vydt6_G-GEWlmZiDNJ6S0sETwFJ42eJg/view?usp=sharing))


### Directory Structure

```plaintext
├── api
├── data
├── graph
├── model
├── repository
├── ui
│   ├── theme
│   ├── list
│   └── detail
├── utils
├── viewmodel
└── MainActivity.kt

# Pokémon Android Application

This is a Pokémon Android application built using Kotlin, Jetpack Compose, Hilt for dependency injection, and the MVVM (Model-View-ViewModel) pattern. The application fetches a list of Pokémon from a server, and upon clicking an item, navigates to a details page to show more information about the selected Pokémon.

## Features

- Fetch Pokémon list from the server.
- Display Pokémon list using Jetpack Compose.
- Navigate to a details page on item click.
- Show detailed information of the selected Pokémon.

## Architecture

The application follows the MVVM architecture pattern:

- **Model:** Represents the data and business logic of the app. In this app, it includes data classes and repository classes for fetching Pokémon data from the server.
- **View:** Composes UI elements using Jetpack Compose.
- **ViewModel:** Acts as a bridge between the Model and the View. It holds and manages UI-related data in a lifecycle-conscious way.

## Dependencies

- **Kotlin**: A modern programming language that makes developers happier.
- **Jetpack Compose**: Android’s modern toolkit for building native UI.
- **Hilt**: A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection.
- **Retrofit**: A type-safe HTTP client for Android and Java.
- **Coroutines**: For managing background threads and simplifying code to run asynchronously.

## Getting Started

### Prerequisites

- Android Studio Bumblebee (or later)
- Gradle 7.0 (or later)
- Internet connection (to fetch Pokémon data)

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/aarush368/pokemon_android_application.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

### Configuration

The project uses Retrofit to fetch data from the server. Make sure you have the correct base URL in your `ApiService` class.

## Usage

1. Launch the app on an Android device or emulator.
2. The main screen will display a list of Pokémon fetched from the server.
3. Click on any Pokémon item to navigate to the details page and view more information.

## Code Overview

### Directory Structure

```plaintext
├── data
├── di
├── model
├── repository
├── utility
├── view
    ├── graph
    ├── ui
        ├── customUi
        ├── screen
        ├── theme
├── viewModel
├── MainActivity.kt
└── MainApplication

