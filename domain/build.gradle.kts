plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.hilt)
    id(Plugins.parcelize)
    kotlin(Plugins.kapt)
}

android {
    namespace = "${ConfigData.applicationId}.domain"
}

dependencies {

    implementation(project(Modules.contract))

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltKapt)
}