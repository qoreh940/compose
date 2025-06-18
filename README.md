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
- **ChecklistScreen:** Demo for editable checklist with custom checkboxes and bottom bar.
- **ImageSliderScreen:** Horizontally scrollable image slider with auto-sliding and indicators.
- **BottomSheetScaffoldScreen:** Sample bottom sheet usage, both in Material2 and Material3 styles.
- **AnimatedCheckBox and Animated UI Feedback:** Custom animations for buttons, checkboxes, and other user interactions.
- 
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
- **Material3 Custom Theming:** The project uses Material3 custom colors, typography, and shapes via the `ui/theme` package.
- **Image and Vector Assets:** Includes sample images and vector icons for use in UI demonstrations.


## 📁 Project Structure
src/
└── main/
├── java/
│   └── com/
│       └── chch/
│           └── mycompose/
│               ├── MainActivity.kt
│               ├── navigation/
│               │   └── ...            # Navigation-related classes
│               ├── ui/
│               │   ├── component/
│               │   │   ├── AnimatedCheckBox.kt
│               │   │   ├── Dialog.kt
│               │   │   ├── Popover.kt
│               │   │   └── ...        # Other custom components
│               │   ├── screen/
│               │   │   ├── ButtonsPage.kt
│               │   │   ├── DialogsPage.kt
│               │   │   ├── NumericKeypadPage.kt
│               │   │   ├── PIPScreen.kt
│               │   │   ├── ChecklistScreen.kt
│               │   │   ├── ImageSliderScreen.kt
│               │   │   ├── BottomSheetScaffoldScreen.kt
│               │   │   └── ...        # Other screens/samples
│               │   ├── theme/
│               │   │   ├── Color.kt
│               │   │   ├── Theme.kt
│               │   │   └── Type.kt
│               │   └── ...            # Other UI files
│               └── ...                # Other app-level files
├── res/
│   ├── drawable/                      # Sample images & icons
│   ├── mipmap/                        # Launcher icons
│   ├── values/                        # colors.xml, themes.xml, strings.xml, etc.
│   └── ...                            # Other resources (e.g. xml/)
└── AndroidManifest.xml

# 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- AndroidX Navigation (Compose)
- Material3 (via Compose)

## 🧪 Modules for Practice

| Feature                | File/Folder                                              |
|------------------------|----------------------------------------------------------|
| Buttons showcase       | `ButtonsPage.kt`                                         |
| Dialog samples         | `DialogsPage.kt`, `Dialog.kt`                            |
| Custom keypad          | `NumericKeypadPage.kt`, `NumericKeypad.kt`               |
| PIP window UI          | `PIPContainer.kt`, `PIPGestureModifier.kt`               |
| Navigation             | `NavRoute.kt`, `NavigationScreen.kt`                     |
| Checklist with animation | `ChecklistScreen.kt`, `AnimatedCheckBox.kt`            |
| Image slider           | `ImageSliderScreen.kt`                                   |
| Bottom sheet samples   | `BottomSheetScaffoldScreen.kt`                           |
| Popover (iOS style)    | `Popover.kt`                                             |

## 📦 How to Run
1. Clone this project
2. Open in Android Studio (Giraffe or later)
3. Run the app on an emulator or device (If build issues occur, use **File > Sync Project with Gradle Files**.)
4. Use the navigation UI to explore different screens

## 📝 Notes
This project is not intended for production use. It’s a playground to try out Compose capabilities through real code.
