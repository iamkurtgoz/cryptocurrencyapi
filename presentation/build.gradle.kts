plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    kotlin(Plugins.kapt)
}

android {
    namespace = "${ConfigData.applicationId}.presentation"

    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = VersionCodes.Others.kotlinCompilerExtensionVersion
    }
}

dependencies {

    //Project
    implementation(project(Modules.core))
    implementation(project(Modules.contract))
    implementation(project(Modules.domain))

    //Androidx
    implementation(Deps.AndroidX.activityCompose)
    implementation(Deps.AndroidX.lifecycle)
    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.pagination)

    //Compose
    implementation(platform(Deps.Compose.composeBom))
    implementation(Deps.Compose.composeUI)
    implementation(Deps.Compose.composeUIGraphics)
    implementation(Deps.Compose.composeUIPreview)
    implementation(Deps.Compose.composeMaterial)
    implementation(Deps.Compose.composeNavigation)
    implementation(Deps.Compose.composeViewModel)
    implementation(Deps.Compose.composeLottie)
    implementation(Deps.Compose.composeHilt)
    implementation(Deps.Compose.composePagination)
    //Glide
    implementation(Deps.Glide.glideLandscapist)

    //Dagger
    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)
}