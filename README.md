# This is a Kotlin Multiplatform project targeting Android, iOS and Desktop (JVM).

### A simple app with a search bar and a list of names. As you type, it filters the list by basic name-matching logic. Designed to work with an API without heavy token usage, and includes a small delay to avoid too many requests while you type.

### How to run it and test it:

---

### Build and Run Android Application

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```