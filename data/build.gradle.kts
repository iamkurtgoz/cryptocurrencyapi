plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    kotlin(Plugins.kapt)
    id(Plugins.ksp)
}

android {
    namespace = "${ConfigData.applicationId}.data"
}

dependencies {

    implementation(project(Modules.contract))
    implementation(project(Modules.core))
    implementation(project(Modules.domain))
    implementation(project(Modules.networdk))
    implementation(project(Modules.local))
    implementation(project(Modules.firebase))

    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.datastore)

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)

    implementation(Deps.Room.room)
    implementation(Deps.Room.roomKtx)
    ksp(Deps.Room.roomCompiler)
}