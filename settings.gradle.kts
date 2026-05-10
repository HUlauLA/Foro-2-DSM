pluginManagement {
    repositories {
        google()        //  Necesario para Android y Firebase
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id("com.android.application") version "8.5.0"
        id("org.jetbrains.kotlin.android") version "1.9.24"
        id("com.google.gms.google-services") version "4.4.1"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()        //  IMPORTANTE
        mavenCentral()
    }
}

rootProject.name = "Control de gastos"
include(":app")
