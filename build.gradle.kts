buildscript {
    repositories {
        google()
        jcenter()
        // Android Build Server
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.0")
        classpath ("com.google.gms:google-services:4.3.3")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.5.2")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
    }
}

// Lists all plugins used throughout the project without applying them.
plugins {
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    kotlin("plugin.serialization") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("io.gitlab.arturbosch.detekt") version "1.18.0-RC2"
    id("com.diffplug.spotless") version "6.0.0"
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        android.set(true)
        verbose.set(true)
        filter {
            exclude { element -> element.file.path.contains("generated/") }
        }
    }
}


subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        config = files("${project.rootDir}/detekt.yml")
        parallel = true
        buildUponDefaultConfig = true
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("${project.rootDir}/build-logic/**/*.kt")
            licenseHeaderFile(
                rootProject.file("${project.rootDir}/spotless/copyright.kt"),
                "^(package|object|import|interface)"
            )
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            // Look for the first line that doesn't have a block comment (assumed to be the license)
            licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
        }
    }
}