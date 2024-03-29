// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.dagger.hilt.android.gradle.plugin) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.app.cach.paparazzi) apply false
}