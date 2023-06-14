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

    //Module
    implementation(project(Modules.contract))

    //Androidx
    api(Deps.AndroidX.workManager)
    api(Deps.AndroidX.workManagerHilt)

    //Network
    api(Deps.Network.retrofit)
    api(Deps.Network.okhttp)
    api(Deps.Network.gsonConverter)
    api(Deps.Network.loggingInterceptor)
    api(Deps.Network.gson)

    //Hilt
    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)

    //Chucker
    debugImplementation(Deps.Chucker.chucker)
    releaseImplementation(Deps.Chucker.chuckerNoOp)
}