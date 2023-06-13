# 🎥 Movies App

The Movies App is an Android application built using Kotlin and follows the MVVM (Model-View-ViewModel) architectural pattern. It utilizes various libraries and components such as Flow, XML, Room, Retrofit, Dagger Hilt, and modularization. The app provides several features for movie enthusiasts, including user authentication, movie search, trivia game, YouTube trailer playback, movie rating, review viewing, movie filtering, watch history, favorites, and user profile.

## 🌟 Features

- **🔒 User Authentication**: Users can log in using their TMDB (The Movie Database) account to access personalized features.

- **🔍 Movie, People, and TV Show Search**: Users can search for movies, people, and TV shows using the app's search functionality.

- **🎮 Trivia Game**: Users can play a trivia game related to movies, testing their knowledge and entertainment expertise.

- **📺 YouTube Trailer Playback**: Users can watch movie trailers directly within the app by playing YouTube videos.

- **⭐️ Movie Rating and Reviews**: Users can rate movies and view reviews submitted by other users.

- **🔍 Movie Filtering**: Users can apply filters to refine their movie search results based on specific criteria or preferences.

- **⌚️ Watch History**: The app keeps track of the user's watch history. When the user visits the movie details screen, the movie is automatically added to the watch history.

- **❤️ Favorites**: Users can add movies to their favorites list and view them later.

- **👤 User Profile**: Users have a profile section where they can view their watch history and favorites.

## 🏗 Architecture

The app follows the recommended architecture from Google with dependency inversion, which includes the following components:

![Architecture](https://github.com/team-chocolate-cake/MovieApp/assets/83292287/932f0c37-c624-487f-a8f2-13980080ede3)

- **UI (Presentation)**: The UI layer handles the app's user interface and interactions. It includes activities, fragments, and XML layouts responsible for displaying information to the user and receiving user input.

- **View Model (Presentation)**: The View Model layer connects the UI with the domain layer. It holds the app's UI-related data using StateFlow or SharedFlow and exposes methods for the UI to interact with.

- **Use Cases (Domain)**: The Domain layer contains the business logic of the app. It defines the use cases and operations that the app can perform.

- **Repository (Data)**: The Repository layer acts as an intermediary between the domain layer and the data sources. It fetches data from remote and local sources, such as APIs or databases, and provides the data to the domain layer.

- **Remote (Data)**: The Remote data source handles data retrieval from remote servers or APIs. In the Movies App, it could be responsible for fetching movie details, trailers, and reviews from TMDB API.

- **Local (Data)**: The Local data source manages data storage and retrieval from local databases, such as Room. It handles tasks like saving user ratings, storing game scores, caching movie details, watch history, and favorites.

## 🧩 Modularization

The app adopts a modularization approach, which involves breaking the application into smaller, independent modules that can be developed, tested, and maintained separately. This allows for better code organization, scalability, and reusability.

The modules in the Movies App could include:

- **app**: The main module responsible for initializing the app, coordinating feature modules, and handling navigation.

- **authentication**: Handles user authentication and account-related functionality.

- **search**: Provides movie, people, and TV show search capabilities.

- **trivia**: Implements the trivia game feature.

- **player**: Handles the YouTube trailer playback functionality.

- **rating**: Manages movie rating and review viewing features.

- **filtering**: Implements the movie filtering functionality.

- **watchhistory**: Manages the watch history functionality.

- **favorites**: Handles the favorites list feature.

- **profile**: Manages the user profile section, including watch history and favorites.

Each module can have its own set of presentation, view model, domain, and repository layers based on its specific requirements.

## 🚀 Getting Started

To run the Movies App on your local machine, follow these steps:

1. Clone the repository from GitHub.

2. Open the project in Android Studio.

3. Build and run the app on an emulator or physical device.

Ensure that you have the necessary dependencies and API keys set up, such as TMDB API key for fetching movie data and YouTube API key for trailer playback.

## 🔚 Conclusion

The Movies App is a comprehensive Android application that combines several features for movie enthusiasts. It follows the MVVM architectural pattern, utilizes various libraries and components, and employs modularization for better code organization and maintainability. Feel free to customize and extend the app according to your specific requirements and preferences.

Note: In the View Model layer, StateFlow or SharedFlow can be used instead of LiveData to handle asynchronous updates of UI-related data and events. StateFlow provides a state-based approach for managing UI states, while SharedFlow can be used for handling UI events. Use these flow-based approaches based on your specific needs and preferences.
