plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    id(Plugins.googleService)
    kotlin(Plugins.kapt)
    id(Plugins.ksp)
}

android {
    namespace = "${ConfigData.applicationId}.firebase"
}

dependencies {

    implementation(project(Modules.contract))

    //Google
    api(platform(Deps.Google.firebaseBom))
    api(Deps.Google.firebaseAnalytics)
    api(Deps.Google.firebaseAuth)
    api(Deps.Google.firebaseFirestore)

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)
}