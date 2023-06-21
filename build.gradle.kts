import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    dependencies {
        classpath(libs.gradle)
        classpath(libs.spotless.plugin.gradle)
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)

    alias(libs.plugins.kotlinJvm).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)

    alias(libs.plugins.compose).apply(false)

    alias(libs.plugins.spotless).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    project.afterEvaluate {
        apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

        if (project.file("build.gradle").exists().not() && project.file("build.gradle.kts").exists().not()) {
            return@afterEvaluate
        }

        configure<SpotlessExtension>() {
            kotlin {
                target("**/*.kt")
                ktlint("0.43.0")
            }
            java {
                target("**/*.java")
                googleJavaFormat()
                indentWithSpaces(2)
                trimTrailingWhitespace()
                removeUnusedImports()
            }
            kotlinGradle {
                target("*.gradle.kts")
                ktlint("0.43.0")
            }
        }
    }
}
