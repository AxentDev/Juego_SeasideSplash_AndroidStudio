// build.gradle.kts (Nivel de Proyecto)

plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false // Actualizada la versión de Kotlin
}

allprojects {
    repositories {
        google() // Repositorio para los plugins de Android
        mavenCentral() // Repositorio para dependencias de Maven
    }
}

buildscript {
    repositories {
        google() // Repositorio para plugins de Android
        mavenCentral() // Repositorio para dependencias de Maven
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Actualizada la versión de Kotlin
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.5") // Plugin para SafeArgs
    }
}
