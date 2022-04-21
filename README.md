## NodeJS Runner for Android

### Usage

* Include the dependency

```groovy
implementation("io.github.thomsontomy:nodejs:0.1.0")
```

* Start the NodeJS code in a separate thread

```kotlin
NodeJsRunner(context).startNodeWithJsCode("<nodejs code>")
```