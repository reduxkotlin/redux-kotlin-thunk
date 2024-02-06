pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.60.5"
    id("com.gradle.enterprise") version "3.12.2"
}

refreshVersions {
    versionsPropertiesFile = rootDir.resolve("gradle/versions.properties")
    extraArtifactVersionKeyRules(rootDir.resolve("gradle/versions.rules"))
}

includeBuild("build-conventions/")

include(
    ":redux-kotlin-thunk"
)

rootProject.name = "Redux-Kotlin-Thunk"
