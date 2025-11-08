pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.google.com")
        maven("https://dl.google.com/dl/android/maven2/")
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com")
        maven("https://dl.google.com/dl/android/maven2/")
        maven("https://jitpack.io")
    }
}

rootProject.name = "homework_t5"
include(":app")
