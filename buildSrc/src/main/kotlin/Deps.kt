object LocalLibraryModules {
    const val networking = ":common:networking"
    const val core = ":common:core"
    const val styles = ":common:styles"
}

object LocalFeatureModules {
    const val launchlist = ":feature-ui:launchlist"
    const val launchlist_api = ":feature-api:launchlist"
}

object Retrofit {
    private const val version = "2.9.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:$version"
}

object OkHttp {
    private const val version = "4.10.0-RC1"

    const val okhttp = "com.squareup.okhttp3:okhttp:$version"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    const val okhttp_mock_webserver = "com.squareup.okhttp3:mockwebserver:$version"
}

object Gson {
    private const val version = "2.8.9"

    const val gson = "com.google.code.gson:gson:$version"
}

object Koin {
    private const val version = "3.1.4"

    const val koin_core = "io.insert-koin:koin-core:$version"
    const val koin_android = "io.insert-koin:koin-android:$version"
}

object Mockk {
    private const val version = "1.12.2"

    const val mockk = "io.mockk:mockk:$version"
    const val mockk_android = "io.mockk:mockk-android:$version"
}

object JUnit {
    private const val version = "4.13.2"

    const val junit = "junit:junit:$version"
}

object Robolectric {
    private const val version = "4.6.1"

    const val robolectric = "org.robolectric:robolectric:$version"
}

object AndroidxJunitExtensions {
    private const val version = "1.1.3"

    const val androidx_test_ext_junit = "androidx.test.ext:junit:$version"
}

object Coroutine {
    private const val version = "1.6.0"

    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
}

object AndroidxCore {
    private const val version = "1.7.0"

    const val core = "androidx.core:core-ktx:$version"
}

object JodaDateTime {
    private const val version = "2.10.9.1"

    const val joda = "net.danlew:android.joda:$version"
}

object ComposeUi {
    private const val version = "1.1.0-rc01"

    const val ui = "androidx.compose.ui:ui:$version"
    const val ui_tooling = "androidx.compose.ui:ui-tooling:$version"
}

object ComposeMaterial {
    private const val version = "1.1.0-rc01"

    const val material = "androidx.compose.material:material:$version"
    const val material_icons_extended = "androidx.compose.material:material-icons-extended:$version"
}

object ComposeFoundation {
    private const val version = "1.1.0-rc01"

    const val foundation = "androidx.compose.foundation:foundation:$version"
    const val foundation_layout = "androidx.compose.foundation:foundation-layout:$version"
}

object ComposeRuntime {
    private const val version = "1.1.0-rc01"

    const val runtime = "androidx.compose.runtime:runtime:$version"
}

object AndroidxFragment {
    private const val version = "1.3.5"

    const val fragment = "androidx.fragment:fragment-ktx:$version"
}

object AndroidxViewModel {
    private const val version = "2.4.0"

    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
}

object AndroidxNavigation {
    private const val version = "2.4.0-rc01"

    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:$version"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:$version"
}

object GoogleMaterial {
    private const val version = "1.5.0-rc01"

    const val material = "com.google.android.material:material:$version"
}
