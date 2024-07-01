# MathLib
Vector and Matrix library.

## How to get
Gradle (kts):
```kotlin
repositories {
    maven {
        name = "alex's repo"
        url = uri("http://207.180.202.42:8080/libs")
        isAllowInsecureProtocol = true
    }
}

dependencies {
  implementation("me.alex_s168:mathlib:0.6h2")
}
```