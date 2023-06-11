import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.application) version VersionCodes.Plugin.application apply false
    id(Plugins.library) version VersionCodes.Plugin.application apply false
    id(Plugins.kotlin) version VersionCodes.Plugin.kotlin apply false
    id(Plugins.hiltClasspath) version VersionCodes.hilt apply false
    id(Plugins.detektPlugin).version(VersionCodes.detekt)
    id(Plugins.ksp) version VersionCodes.Plugin.ksp apply false
}

buildscript {
    dependencies {
        classpath(Deps.Detekt.detekt)
    }
    repositories {
        mavenCentral()
    }
}

subprojects {
    project.plugins.applyBaseConfig(project)
}

fun BaseExtension.baseConfig() {
    setCompileSdkVersion(ConfigData.compileSdk)

    defaultConfig.apply {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk

        testInstrumentationRunner = ConfigData.testRunner
    }

    compileOptions.apply {
        sourceCompatibility = ConfigData.CompileOptions.javaSourceCompatibility
        targetCompatibility = ConfigData.CompileOptions.javaSourceCompatibility
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = ConfigData.CompileOptions.kotlinJvmTarget
            languageVersion = ConfigData.CompileOptions.languageVersion
            freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
            freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    dependencies {
        detektPlugins(Deps.Detekt.detektAutoFormat)
        detektPlugins(Deps.Detekt.detektLibraries)
    }
}

fun LibraryExtension.libraryConfig() {
    buildTypes {
        getByName(ProductFlavors.builtTypeDebug) {
            isMinifyEnabled = false
            isJniDebuggable = true
            isRenderscriptDebuggable = true
        }

        getByName(ProductFlavors.builtTypeRelease) {
            isMinifyEnabled = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
        }
    }

    defaultConfig.apply {
        consumerProguardFiles("consumer-rules.pro")
    }
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        baseConfig()
                    }
            }

            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        baseConfig()
                        libraryConfig()
                    }
            }
        }
    }
}

val projectSource = file(projectDir)
val configFile = files("$rootDir/detekt/detekt.yml")
val baselineFile = file("$rootDir/detekt/baseline.xml")
val kotlinFiles = "**/*.kt"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"
val buildSrcFiles = "**/buildSrc/**"
val deprecatedApiModule = "**/api/**"
val deprecatedCommonModule = "**/common/**"
val htmlOutputFile = file("$rootDir/reports/DetektReport.html")

tasks.withType<Detekt>().configureEach {
    jvmTarget = ConfigData.CompileOptions.languageVersion
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = ConfigData.CompileOptions.languageVersion
}

tasks.register<Detekt>("detektAll") {
    description = "Custom DETEKT TASK, you can run with  ./gradlew detektAll"
    parallel = true
    ignoreFailures = false
    buildUponDefaultConfig = true
    setSource(projectSource)
    baseline.set(baselineFile)
    config.setFrom(configFile)
    include(kotlinFiles)
    exclude(resourceFiles, buildFiles, buildSrcFiles)
    reports {
        html.required.set(true)
        html.outputLocation.set(file("$rootDir/reports/DetektReport.html"))
    }
    autoCorrect = true
}