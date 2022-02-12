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

    // Local library modules
    implementation(project(LocalLibraryModules.core))
    implementation(project(LocalLibraryModules.networking))

    // Androidx
    implementation(AndroidxCore.core)

    // Networking
    implementation(Retrofit.retrofit)
    implementation(Gson.gson)

    // Koin
    implementation(Koin.koin_core)

    // DateTime
    implementation(JodaDateTime.joda)

    // Coroutines
    implementation(Coroutine.coroutines_core)

    // Testing
    testImplementation(Robolectric.robolectric)
    testImplementation(JUnit.junit)
    testImplementation(Mockk.mockk)
    testImplementation(AndroidxJunitExtensions.androidx_test_ext_junit)
    testImplementation(Coroutine.coroutines_test)
}