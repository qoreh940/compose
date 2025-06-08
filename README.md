# Compose UI Sample Project
A sample project to practice and explore Jetpack Compose features in Android development.

## 🚀 Purpose
This project was created as a learning sandbox to explore and understand core Jetpack Compose concepts, such as:

- Composable UI elements
- Custom modifiers and gestures
- Navigation with Compose
- Dialogs, popups, and layout control
- Custom numeric keypad input
- Picture-in-Picture style UI components

## 🧱 Features

- **ButtonsPage**: Demonstrates usage of various Compose Button types.
- **DialogsPage**: Shows multiple dialog and popup implementations.
- **Popover**: Custom popup-style composable mimicking iOS-style popovers.
- **NumericKeypadPage**: UI with a custom number-only input keypad.
- **PIPScreen**: A draggable, dockable floating container (Picture-in-Picture style).
- **Navigation**: Basic Compose navigation using `NavRoute` and `NavigationScreen`.

## 🧩 Composables and Jetpack Compose Features Used
This project explores a wide range of composables and UI constructs available in Jetpack Compose:

- **Navigation**: Used `NavHost` and `NavController` to handle screen transitions and routing.
- **Scaffold**: Utilized `Scaffold` with `TopAppBar` and content slots to create a consistent page layout.
- **LazyColumn & Cards**: Implemented `LazyColumn` to display scrollable lists and `OutlinedCard` for layout organization.
- **Buttons**:
    - `Button`, `FilledTonalButton`, `OutlinedButton`, `ElevatedButton` – to explore different Material styles.
    - `IconButton` – for compact icon-based interactions.
    - `Card` with expandable content – to test custom button-like behavior.
- **Dialogs**:
    - `AlertDialog` and `Dialog` for showing modal interfaces and confirmations.
- **Popups**:
    - `Popup` and a custom `RowPopover` composable – designed to mimic iOS-style popovers using Compose's `Popup` API.

## 📁 Project Structure
src/
├── main/
│   ├── java/com/example/compose/
│   │   ├── ui/screen/         # Various sample pages
│   │   ├── ui/component/      # Reusable UI components
│   │   ├── ui/theme/          # Custom Material theme setup
│   │   ├── core/navigation/   # Navigation logic
│   ├── res/                   # Resources (icons, strings, colors)
│   ├── AndroidManifest.xml


# 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- AndroidX Navigation (Compose)
- Material3 (via Compose)

## 🧪 Modules for Practice

| Feature            | File/Folder                                      |
|--------------------|--------------------------------------------------|
| Buttons showcase   | `ButtonsPage.kt`                                 |
| Dialog samples     | `DialogsPage.kt`, `Dialog.kt`                    |
| Custom keypad      | `NumericKeypadPage.kt`, `NumericKeypad.kt`       |
| PIP window UI      | `PIPContainer.kt`, `PIPGestureModifier.kt`       |
| Navigation         | `NavRoute.kt`, `NavigationScreen.kt`             |

## 📦 How to Run
1. Clone this project
2. Open in Android Studio (Giraffe or later)
3. Run the app on an emulator or device (If build issues occur, use **File > Sync Project with Gradle Files**.)
4. Use the navigation UI to explore different screens

## 📝 Notes
This project is not intended for production use. It’s a playground to try out Compose capabilities through real code.
