plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    kotlin(Plugins.kapt)
}

android {
    namespace = "${ConfigData.applicationId}.network"
}

dependencies {

    implementation(project(Modules.contract))

    api(Deps.Network.retrofit)
    api(Deps.Network.okhttp)
    api(Deps.Network.gsonConverter)
    api(Deps.Network.loggingInterceptor)
    api(Deps.Network.gson)

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)

    debugImplementation(Deps.Chucker.chucker)
    releaseImplementation(Deps.Chucker.chuckerNoOp)
}