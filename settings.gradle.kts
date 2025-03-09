pluginManagement {
    repositories {
        google() // Repositorio para los plugins de Android
        gradlePluginPortal() // Repositorio para plugins de Gradle
        mavenCentral() // Repositorio para dependencias Maven
    }
}

dependencyResolutionManagement {
    repositories {
        google() // Repositorio para dependencias de Android
        mavenCentral() // Repositorio para dependencias Maven
    }
}

rootProject.name = "Seaside Splash"
include(":app")
