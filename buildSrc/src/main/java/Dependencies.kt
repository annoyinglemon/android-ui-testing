object Versions {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32

    const val ANDROID_GRADLE = "7.0.4"

    const val COROUTINES = "1.6.1"
    const val KOTLIN = "1.6.10"
    const val JSR = "1.0"

    const val APP_COMPAT = "1.4.0"
    const val COMPOSE = "1.1.1"
    const val COMPOSE_ACTIVITY = "1.4.0"
    const val CONSTRAINT_LAYOUT = "2.1.2"
    const val CORE_KTX = "1.7.0"
    const val DAGGER = "2.40.3"
    const val FIREBASE = "29.2.1"
    const val GOOGLE_SERVICES = "4.3.10"
    const val LIFECYCLE_KTX = "2.4.0"
    const val MATERIAL = "1.4.0"
    const val MULTIDEX = "2.0.1"
    const val NAVIGATION = "2.3.5"
    const val UI_KTX = "1.4.0"

    const val RECLAIM = "2.1.1"

    const val ESPRESSO = "3.4.0"
    const val JUNIT = "4.13.2"
    const val JUNIT_TEST_EXT = "1.1.3"
    const val MOCKK = "1.12.1"
    const val TRUTH_TEST_EXT = "1.4.0"
}

object Deps {
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.UI_KTX}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.COMPOSE}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.COMPOSE}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val composeThemeAdapter = "com.google.android.material:compose-theme-adapter:${Versions.COMPOSE}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val dagger = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val firebase = "com.google.firebase:firebase-bom:${Versions.FIREBASE}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.UI_KTX}"
    const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val materialDesign = "com.google.android.material:material:${Versions.MATERIAL}"
    const val multidex = "androidx.multidex:multidex:${Versions.MULTIDEX}"
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"

    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val javaxAnnotation = "javax.annotation:jsr250-api:${Versions.JSR}"

    const val reclaim = "com.github.fueled:reclaim:${Versions.RECLAIM}"
}

object TestDeps {
    const val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
    const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
    const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.UI_KTX}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO}"
    const val jUnit = "junit:junit:${Versions.JUNIT}"
    const val mockk = "io.mockk:mockk-android:${Versions.MOCKK}"
    const val navTesting = "androidx.navigation:navigation-testing:${Versions.NAVIGATION}"
    const val testExtJUnit = "androidx.test.ext:junit:${Versions.JUNIT_TEST_EXT}"
    const val testExtTruth = "androidx.test.ext:truth:${Versions.TRUTH_TEST_EXT}"
}

object Plugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val navSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
    const val googleServices = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
}

object AppPlugins {
    const val android = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinParcelize = "kotlin-parcelize"
    const val navSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val googleServices = "com.google.gms.google-services"
}