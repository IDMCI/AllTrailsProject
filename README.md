# AllTrailsProject #
This is an Android application built using MVVM Clean Architecture with Jetpack Compose.

> NOTE: This is a take-home project for interviewing purposes only. 

## Features: ##

- MVVM architecture for clean separation of concerns
- Clean Architecture for independent and testable layers
- Jetpack Compose for a modern and declarative UI
- Hilt for dependency injection
- Hilt ViewModel for simplified ViewModel creation
- Android Navigation for seamless app navigation
- Google Maps SDK for displaying places
- Google Places API (not SDK) for retrieving places
- Integration tests for remote endpoint interaction

## Requirements: ##

Project requirements (Link is not shared here. See email for details)
Android Studio (latest version recommended)
Java 19 (tested with Azul Zulu 19.0.2)

## State of App: ##
- BUG: Possible API version issues. Emulator works on API 31, but stopped working on my personal device (API 34). Will investigate later. 

## Getting Started: ##

1. Clone the repository: `git clone https://github.com/IDMCI/AllTrailsProject`
2. Open the project in Android Studio.
3. Add the Google API key into your [secrets.properties](https://developers.google.com/maps/documentation/places/android-sdk/secrets-gradle-plugin) file under `MAPS_API_KEY`
4. Run the app on an Android device or emulator with API level `28` or higher. Target SDK is set to API level `34`.

## Project Structure: ##

The project is organized following the clean architecture principles, with separate folders for:

- domain: Business logic independent of Android frameworks.
- datasource: Data access layer (repositories) with abstractions for local and remote data sources.
- di: Dependency injection configuration using Hilt.
- presentation: UI logic with Jetpack Compose and ViewModels.

## Testing: ##

The project does NOT currently include unit tests. However, the libraries for `mockito`, `mockk`, `junit` and others have been imported.

### ScreenShot: ###


<img height="540" alt="working_gif" src="https://github.com/user-attachments/assets/e91b503d-e504-4271-8d2d-9dc3e25a880d">
