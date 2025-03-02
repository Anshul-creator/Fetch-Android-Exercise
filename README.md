# Fetch Android Exercise

This Android application fetches JSON data from a remote API, processes it and displays the results in a paginated RecyclerView. The app is built using Java and leverages Retrofit for networking and standard Android components for the UI.

## Features

- **Data Fetching**: Retrieves a list of items from a remote JSON endpoint.
- **Data Processing**:
  - Filters out items with empty or null names.
  - Groups items by listId and sorts them.
  - Inserts headers for each group.
- **Pagination**: Displays a fixed number of items per page (50 items per page) with Next/Previous navigation.
- **User Interface**:
  - Clean and easy-to-read list with group headers (bolded and larger text) and data items (prefixed with a counter).

## Getting Started

### Prerequisites

- Android Studio (latest stable version recommended)
- An Android device or emulator running API level 21 or higher
- Internet connectivity (to fetch the JSON data)

### Installation

#### Clone the Repository

```bash
git clone https://github.com/Anshul-creator/Fetch-Android-Exercise.git
cd Fetch-Android-Exercise
```

#### Open the Project in Android Studio

1. Launch Android Studio.
2. Select **File → Open** and navigate to the cloned repository.
3. Wait for Gradle to sync and download necessary dependencies.

### Running the App

1. Connect an Android device or start an emulator.
2. Click the **Run** button in Android Studio.
3. The app will launch, fetch data from the remote endpoint, and display it in a paginated list.
4. Use the **Next** and **Previous** buttons to navigate between pages.

## Technologies Used

- **Java**: Primary programming language for the app.
- **Android SDK**: Provides the necessary APIs for building Android apps.
- **Retrofit**: Handles network requests and JSON parsing via Gson.
- **RecyclerView**: Displays a scrolling list of items.
- **Gradle**: Manages project dependencies and builds the app.