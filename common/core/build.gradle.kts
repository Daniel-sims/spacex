plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {

    defaultConfig {
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
}

dependencies {

    // Androidx
    implementation(AndroidxCore.core)

    // Coroutines
    implementation(Coroutine.coroutines_core)

    // Koin
    implementation(Koin.koin_core)

    // Testing
    testImplementation(Robolectric.robolectric)
    testImplementation(JUnit.junit)
    testImplementation(AndroidxJunitExtensions.androidx_test_ext_junit)
}