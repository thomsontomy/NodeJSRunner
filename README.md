## NodeJS Runner for Android

[![CircleCI](https://circleci.com/gh/thomsontomy/NodeJSRunner/tree/main.svg?style=svg)](https://circleci.com/gh/thomsontomy/NodeJSRunner/tree/main)

### Usage

* Include the dependency

```groovy
implementation("io.github.thomsontomy:nodejs:0.1.0")
```

* Start the NodeJS code in a separate thread

```kotlin
NodeJsRunner(context).startNodeWithJsCode("<nodejs code>")
```