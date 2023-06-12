import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.invoke

object VersionCodes {

    const val detekt = "1.22.0"
    const val hilt = "2.46"
    const val chucker = "3.5.2"
    const val room = "2.5.1"

    object AndroidX {
        const val activityCompose = "1.7.2"
        const val lifecycle = "2.6.1"
        const val appcompat = "1.6.1"
        const val core = "1.10.1"
        const val material = "1.9.0"
        const val splashScreen = "1.0.1"
        const val pagination = "3.1.1"
        const val datastore = "1.0.0"
    }

    object Compose {
        const val composeBom = "2022.10.00"
        const val composeNav = "2.5.3"
        const val composeHilt = "1.0.0"
        const val composeLottie = "6.0.0"
        const val composeViewModel = "2.6.1"
        const val composePagination = "3.2.0-beta01"
    }

    object Google {
        const val googleService = "4.3.15"
        const val firebaseBom = "32.1.0"
    }

    object Network {
        val retrofit = "2.9.0"
        val okhttp3 = "5.0.0-alpha.2"
        val gsonConverter = "2.9.0"
        val gson = "2.10.1"
    }

    object Glide {
        val glideLandscapist = "2.2.1"
    }

    object Plugin {
        const val application = "8.0.1"
        const val kotlin = "1.8.10"
        const val ksp = "1.8.10-1.0.9"

        object Kotlin {
            const val kapt = "1.8.20"
        }
    }

    object Others {
        val inject = "1"
        val kotlinCompilerExtensionVersion = "1.4.2"
    }
}