plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    kotlin(Plugins.kapt)
    id(Plugins.ksp)
}

android {
    namespace = "${ConfigData.applicationId}.local"
}

dependencies {

    implementation(project(Modules.contract))

    api(Deps.Room.room)
    api(Deps.Room.roomKtx)
    ksp(Deps.Room.roomCompiler)

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)
}