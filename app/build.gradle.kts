plugins {
    id(Plugins.application)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    id(Plugins.parcelize)
    kotlin(Plugins.kapt)
}

android {
    namespace = ConfigData.applicationId
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.computedVersionCode()
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    @Suppress("UnstableApiUsage")
    buildTypes {
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = ConfigData.minifyEnabledDebug
            isDebuggable = true
        }
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = ConfigData.minifyEnabledRelease
            isShrinkResources = true
            isDebuggable = false
        }
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = VersionCodes.Others.kotlinCompilerExtensionVersion
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }
}

dependencies {

    //Project
    implementation(project(Modules.firebase))
    implementation(project(Modules.contract))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.presentation))

    //Androidx
    implementation(Deps.AndroidX.activityCompose)
    implementation(Deps.AndroidX.lifecycle)
    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.splashScreen)

    //Compose
    implementation(platform(Deps.Compose.composeBom))
    implementation(Deps.Compose.composeUI)
    implementation(Deps.Compose.composeUIGraphics)
    implementation(Deps.Compose.composeUIPreview)
    implementation(Deps.Compose.composeMaterial)

    //Dagger
    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)
}