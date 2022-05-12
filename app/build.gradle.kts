plugins {
    id(AppPlugins.android)
    id(AppPlugins.kotlinAndroid)
    id(AppPlugins.kotlinKapt)
    id(AppPlugins.kotlinParcelize)
    id(AppPlugins.navSafeArgs)
    id(AppPlugins.googleServices)
}

apply(from = "$rootDir/version.gradle.kts")
val buildVersionCode: () -> Int by project.ext
val buildVersionName: () -> String by project.ext

android {
    signingConfigs {
        create("release") {
            storeFile = file("../signing/lemond.keystore")
            storePassword = System.getenv("KEYSTORE_STORE_PASSWORD")
            keyAlias = System.getenv("KEYSTORE_KEY_ALIAS")
            keyPassword = System.getenv("KEYSTORE_KEY_PASSWORD")
        }
        getByName("debug") {
            storeFile = file("../signing/debug.keystore")
            keyAlias = "androiddebugkey"
            storePassword = "android"
            keyPassword = "android"
        }
    }
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "kurt.lemond.uitesting"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = buildVersionCode()
        versionName = buildVersionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

dependencies {
    implementation(Deps.activityKtx)
    implementation(Deps.appCompat)
    implementation(Deps.composeActivity)
    implementation(Deps.composeAnimation)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeUiTooling)
    implementation(Deps.composeThemeAdapter)
    implementation(Deps.constraintLayout)
    implementation(Deps.coreKtx)
    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)
    implementation(platform(Deps.firebase))
    implementation(Deps.fragmentKtx)
    implementation(Deps.navFragmentKtx)
    implementation(Deps.navUiKtx)
    implementation(Deps.materialDesign)
    implementation(Deps.multidex)
    implementation(Deps.runtimeKtx)
    implementation(Deps.viewModelKtx)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.coroutinesCore)
    implementation(Deps.javaxAnnotation)
    implementation(Deps.reclaim)

    testImplementation(TestDeps.jUnit)

    androidTestImplementation(TestDeps.navTesting)
    androidTestImplementation(TestDeps.testExtJUnit)
    androidTestImplementation(TestDeps.espressoCore)
    androidTestImplementation(TestDeps.espressoContrib)
    androidTestImplementation(TestDeps.testExtTruth)
    androidTestImplementation(TestDeps.mockk)
    kaptAndroidTest(Deps.daggerCompiler)

    androidTestImplementation(TestDeps.composeJUnit)
    debugImplementation(TestDeps.composeManifest)

    debugImplementation(TestDeps.fragmentTest)

}