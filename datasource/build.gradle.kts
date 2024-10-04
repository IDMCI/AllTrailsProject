plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.devtools.ksp)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.duncanclark.datasource"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    hilt {
        enableAggregatingTask = true
    }
    secrets {
        // To add your Maps API key to this project:
        // 1. If the secrets.properties file does not exist, create it in the same folder as the local.properties file.
        // 2. Add this line, where YOUR_API_KEY is your API key:
        //        MAPS_API_KEY=YOUR_API_KEY
        propertiesFileName = "secrets.properties"

        // A properties file containing default secret values. This file can be
        // checked in version control.
        defaultPropertiesFileName = "local.properties"

        // Configure which keys should be ignored by the plugin by providing regular expressions.
        // "sdk.dir" is ignored by default.
        ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
        ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.serialization)
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.retrofit.mock)
}