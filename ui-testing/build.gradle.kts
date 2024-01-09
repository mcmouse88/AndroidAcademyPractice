import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android.gradle.plugin)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.mcmouse88.ui_testing"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.mcmouse88.ui_testing"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(rootProject.file("apikey.properties").inputStream())
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "THE_MOVIE_DB_API_KEY", "\"${properties.getProperty("THE_MOVIE_DB_API_KEY")}\"")
        buildConfigField("String", "BASE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w300\"")
        buildConfigField("String", "BROWSE_MOVIE_BASE_URL", "\"https://www.themoviedb.org/movie/\"")

        ksp {
            arg("room.generateKotlin", "true")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.squareup.okhttp3.logging.interceptor)

    implementation(libs.jetbrains.kotlinx.coroutines.core)
    implementation(libs.jetbrains.kotlinx.coroutines.android)

    implementation(libs.google.dagger.hilt.android)
    ksp(libs.google.dagger.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.io.coil.kt.compose)

    implementation(libs.jakewharton.timber)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}