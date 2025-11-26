# Time Tracker

A simple Android time tracking app built with Kotlin and Jetpack Compose.

## Features

- ✅ Track time for multiple tasks
- ✅ Start/Stop timers with real-time updates
- ✅ View statistics and task breakdown
- ✅ Delete tasks
- ✅ Dark mode support
- ✅ Material 3 design

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM
- **Database**: Room
- **Navigation**: Compose Navigation
- **State Management**: ViewModel + StateFlow
- **Testing**: JUnit

## How to Run

1. Clone the repository

```bash
   git clone https://github.com/yourusername/TimeTracker.git
```

2. Open in Android Studio (Hedgehog | 2023.1.1 or later)
3. Sync Gradle files
4. Run on an emulator or device (API 26+)

## Project Structure

```
app/
├── data/          # Room database entities and DAOs
├── viewmodel/     # ViewModels and business logic
├── ui/
│   ├── screens/   # Composable screens
│   └── theme/     # Material 3 theme
└── MainActivity   # App entry point
```

## Requirements Met

- ✅ 3+ screens (List, Add, Stats)
- ✅ Navigation (Compose Navigation)
- ✅ State management (ViewModel + StateFlow)
- ✅ Data persistence (Room)
- ✅ Material 3 + Dark mode
- ✅ Input validation
- ✅ Testing (Unit tests)

## Note

Some parts of this code were written with AI assistance.

## License

MIT License
