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

    // Networking
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofit_gson_converter)
    implementation(Gson.gson)
    implementation(OkHttp.okhttp_interceptor)

    // Koin DI
    implementation(Koin.koin_core)

    // Testing
    testImplementation(Robolectric.robolectric)
    testImplementation(JUnit.junit)
    testImplementation(Mockk.mockk)
    testImplementation(OkHttp.okhttp_mock_webserver)
    testImplementation(AndroidxJunitExtensions.androidx_test_ext_junit)
    testImplementation(Coroutine.coroutines_test)
}