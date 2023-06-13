object Deps {

    object AndroidX {
        val activityCompose = buildString {
            append("androidx.activity:activity-compose:")
            append(VersionCodes.AndroidX.activityCompose)
        }
        val lifecycle = buildString {
            append("androidx.lifecycle:lifecycle-runtime-ktx:")
            append(VersionCodes.AndroidX.lifecycle)
        }
        val core = buildString {
            append("androidx.core:core-ktx:")
            append(VersionCodes.AndroidX.core)
        }
        val material = buildString {
            append("com.google.android.material:material:")
            append(VersionCodes.AndroidX.material)
        }
        val splashScreen = buildString {
            append("androidx.core:core-splashscreen:")
            append(VersionCodes.AndroidX.splashScreen)
        }
        val pagination = buildString {
            append("androidx.paging:paging-runtime:")
            append(VersionCodes.AndroidX.pagination)
        }
        val datastore = buildString {
            append("androidx.datastore:datastore-preferences:")
            append(VersionCodes.AndroidX.datastore)
        }
    }

    object Compose {
        val composeBom = buildString {
            append("androidx.compose:compose-bom:")
            append(VersionCodes.Compose.composeBom)
        }
        val composeUI = buildString {
            append("androidx.compose.ui:ui")
        }
        val composeUIGraphics = buildString {
            append("androidx.compose.ui:ui-graphics")
        }
        val composeUIPreview = buildString {
            append("androidx.compose.ui:ui-tooling-preview")
        }
        val composeMaterial = buildString {
            append("androidx.compose.material3:material3")
        }
        val composeNavigation = buildString {
            append("androidx.navigation:navigation-compose:")
            append(VersionCodes.Compose.composeNav)
        }
        val composeViewModel = buildString {
            append("androidx.lifecycle:lifecycle-viewmodel-compose:")
            append(VersionCodes.Compose.composeViewModel)
        }
        val composeHilt = buildString {
            append("androidx.hilt:hilt-navigation-compose:")
            append(VersionCodes.Compose.composeHilt)
        }
        val composeLottie = buildString {
            append("com.airbnb.android:lottie-compose:")
            append(VersionCodes.Compose.composeLottie)
        }
        val composePagination = buildString {
            append("androidx.paging:paging-compose:")
            append(VersionCodes.Compose.composePagination)
        }
    }

    object Google {
        val googleServices = buildString {
            append("com.google.gms:google-services:")
            append(VersionCodes.Google.googleService)
        }
        val firebaseBom = buildString {
            append("com.google.firebase:firebase-bom:")
            append(VersionCodes.Google.firebaseBom)
        }
        val firebaseAnalytics = buildString {
            append("com.google.firebase:firebase-analytics-ktx")
        }
        val firebaseAuth = buildString {
            append("com.google.firebase:firebase-auth-ktx")
        }
        val firebaseFirestore = buildString {
            append("com.google.firebase:firebase-firestore-ktx")
        }
    }

    object Hilt {
        val hilt = buildString {
            append("com.google.dagger:hilt-android:")
            append(VersionCodes.hilt)
        }
        val hiltKapt = buildString {
            append("com.google.dagger:hilt-android-compiler:")
            append(VersionCodes.hilt)
        }
    }

    object Room {
        val room = buildString {
            append("androidx.room:room-runtime:")
            append(VersionCodes.room)
        }
        val roomKtx = buildString {
            append("androidx.room:room-ktx:")
            append(VersionCodes.room)
        }
        val roomCompiler = buildString {
            append("androidx.room:room-compiler:")
            append(VersionCodes.room)
        }
    }

    object Detekt {
        val detekt = buildString {
            append("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:")
            append(VersionCodes.detekt)
        }
        val detektAutoFormat = buildString {
            append("io.gitlab.arturbosch.detekt:detekt-formatting:")
            append(VersionCodes.detekt)
        }
        val detektLibraries = buildString {
            append("io.gitlab.arturbosch.detekt:detekt-rules-libraries:")
            append(VersionCodes.detekt)
        }
    }

    object Network {
        val retrofit = buildString {
            append("com.squareup.retrofit2:retrofit:")
            append(VersionCodes.Network.retrofit)
        }
        val okhttp = buildString {
            append("com.squareup.okhttp3:okhttp:")
            append(VersionCodes.Network.okhttp3)
        }
        val gsonConverter = buildString {
            append("com.squareup.retrofit2:converter-gson:")
            append(VersionCodes.Network.gsonConverter)
        }
        val loggingInterceptor = buildString {
            append("com.squareup.okhttp3:logging-interceptor:")
            append(VersionCodes.Network.okhttp3)
        }
        val gson = buildString {
            append("com.google.code.gson:gson:")
            append(VersionCodes.Network.gson)
        }
    }

    object Glide {
        val glideLandscapist = buildString {
            append("com.github.skydoves:landscapist-glide:")
            append(VersionCodes.Glide.glideLandscapist)
        }
    }

    object Chucker {
        val chucker = buildString {
            append("com.github.chuckerteam.chucker:library:")
            append(VersionCodes.chucker)
        }
        val chuckerNoOp = buildString {
            append("com.github.chuckerteam.chucker:library-no-op:")
            append(VersionCodes.chucker)
        }
    }

    object Others {
        val inject = buildString {
            append("javax.inject:javax.inject:")
            append(VersionCodes.Others.inject)
        }
    }
}