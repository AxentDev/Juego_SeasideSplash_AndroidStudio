plugins {
    id("com.android.application")
    kotlin("android") version "2.0.0" // Aseguramos la versión de Kotlin 1.8.0
    id("androidx.navigation.safeargs.kotlin") // Plugin para SafeArgs
    id("kotlin-kapt") // Si usas KAPT para anotaciones
}

android {
    compileSdk = 35 // Mantén la versión de SDK reciente, está bien usar la 33

    namespace = "com.example.seasidesplash"

    defaultConfig {
        applicationId = "com.example.seasidesplash"
        minSdk = 21 // Versión mínima de SDK
        //noinspection EditedTargetSdkVersion
        targetSdk = 35 // Versión objetivo del SDK
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false // Desactiva la minificación para la versión release
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Compatibilidad con Java 1.8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8" // Kotlin con JVM 1.8
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true // Incluir recursos en pruebas unitarias
    }
}

dependencies {
    // AndroidX Core
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Jetpack Compose (alineado con una versión estable conocida)
    implementation("androidx.compose.ui:ui:1.7.6")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.6")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.6")

    // Navegación y actividades
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.activity:activity-compose:1.10.0")

    // Pruebas unitarias
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
