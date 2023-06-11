plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
}

android {
    namespace = "${ConfigData.applicationId}.contract"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    implementation(Deps.Others.inject)
}
