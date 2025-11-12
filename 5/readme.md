# Android Multi-Counter App

This is a simple Android application that demonstrates state management in Jetpack Compose. It features three independent counter units.

## Features

- **Independent Counters:** The app displays three counters arranged vertically. Each counter can be incremented or decremented without affecting the others.
- **Editable Start Value:** The current count is shown in a `TextField`, allowing the user to tap and type in any starting number.
- **State Management:** The app uses `remember` and `mutableStateOf` to manage the state for each counter instance, showcasing a core concept of Jetpack Compose.
- **Layouts:** The main screen uses a `Column` for vertical arrangement, and each counter unit uses a `Row` for horizontal arrangement of its buttons and text field.

## Program Output (Screenshot)![App Screenshot](screenshot.png)
