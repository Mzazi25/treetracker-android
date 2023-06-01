plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id ("androidx.navigation.safeargs")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")

}

android {
    compileSdkVersion (31)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "org.greenstand.android.TreeTracker"
        minSdkVersion (21)
        targetSdkVersion (31)
        versionCode = 196
        versionName = "2.1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas".toString()
            }
        }
    }
    buildFeatures {
        compose = true
        viewBinding =true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    buildTypes {
        named("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

//        named("debug") {
//            applicationIdSuffix = ".debug"
//        }

//        named("beta") {
//            applicationIdSuffix = ".test"
//            isDebuggable = true
//        }


    }
    packagingOptions {
        resources {
            pickFirsts.add("/META-INF/DEPENDENCIES")
            pickFirsts.add("/META-INF/LICENSE")
            pickFirsts.add("/META-INF/LICENSE.txt")
            pickFirsts.add("/META-INF/license.txt")
            pickFirsts.add("/META-INF/NOTICE")
            pickFirsts.add("/META-INF/NOTICE.txt")
            pickFirsts.add("/META-INF/notice.txt")
            pickFirsts.add("/META-INF/ASL2.0")
            pickFirsts.add("/META-INF/io.netty.versions.properties")
            pickFirsts.add("/META-INF/INDEX.LIST")
        }
    }

}

dependencies {

    // 2.0: https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/setup-project-gradle.html
    // android s3 sdk does not support transfer acceleration
    implementation (libs.amazon.sdk.core )
    implementation (libs.amazon.sdk.s3 )

    // Koin
    testImplementation (libs.koin.test)
    implementation (libs.koin.androidx.compose)
    implementation (libs.koin.android )

    implementation (libs.androidx.appcompat)
    implementation (libs.androidx.multidex)
    implementation (libs.androidx.exifinterface)
    implementation (libs.androidx.lifecycle.viewModel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.work.ktx)

    // Compose
    implementation (libs.androidx.compose.runtime)
    implementation (libs.androidx.compose.runtime.livedata)
    implementation (libs.androidx.compose.foundation)
    implementation (libs.androidx.compose.foundation.layout)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.ui.tooling)
    implementation (libs.androidx.compose.material)
    implementation (libs.androidx.compose.animation)
    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.navigation.compose)

    //Permissions management library for Jetpack Compose
    implementation (libs.accompanist.permissions)

    // Kotlin
    implementation (libs.kotlin.stdlib)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.datetime)
    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    //Database
    implementation (libs.room.runtime)
    implementation (libs.room.ktx)
    kapt (libs.room.compiler)

    api (libs.retrofit.converter.gson)
    implementation (libs.retrofit.core)
    implementation (libs.okhttp.logging)

    //CameraX
    implementation (libs.androidx.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.extensions)

    api (libs.timber)
    implementation (libs.androidx.legacy.support)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.iid)

    // Test
    testImplementation(libs.androidx.core.ktx )
    testImplementation(libs.mock)
    testImplementation(libs.junit4)
    testImplementation(libs.room.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.support.test.runner)
    androidTestImplementation(libs.support.test.espresso)
    testImplementation(libs.roboelectric)
    androidTestImplementation(libs.turbine)
    testImplementation(libs.turbine)

}

configurations.all {
    resolutionStrategy {
        preferProjectModules()
        force("com.google.code.gson:gson:2.8.5")
    }
}
