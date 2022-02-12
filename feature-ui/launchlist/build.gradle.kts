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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {

    // Local modules
    implementation(project(LocalFeatureModules.launchlist_api))
    implementation(project(LocalLibraryModules.styles))
    implementation(project(LocalLibraryModules.core))
    // Fragment
    implementation(AndroidxFragment.fragment)

    // Viewmodel
    implementation(AndroidxViewModel.viewmodel)

    // Koin
    implementation(Koin.koin_android)
    implementation(Koin.koin_core)

    // Joda
    implementation(JodaDateTime.joda)

    // Compose stuff
    implementation(ComposeUi.ui)
    implementation(ComposeUi.ui_tooling)
    implementation(ComposeMaterial.material)
    implementation(ComposeMaterial.material_icons_extended)
    implementation(ComposeFoundation.foundation)

    // Coil
    implementation(Coil.coil)

    // Navigation
    implementation(AndroidxNavigation.navigation_fragment)
    implementation(AndroidxNavigation.navigation_ui)
}