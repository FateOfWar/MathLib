plugins {
    kotlin("jvm") version "1.9.21"
    id("maven-publish")
}

group = "me.alex_s168"
version = "0.6h2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}