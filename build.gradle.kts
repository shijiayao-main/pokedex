import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
//    apply(from = rootProject.file("spotless/build.gradle.kts"))
    dependencies {
        classpath(libs.gradle)
        classpath(libs.spotless.plugin.gradle)
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    alias(libs.plugins.spotless).apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
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

//spotless {
//    kotlin {
//        target("**/*.kt")
//        ktlint("0.33.0").userData(
//            mapOf(
//                "indent_size" to "2",
//                "continuation_indent_size" to "2"
//            )
//        )
//    }
//    java {
//        target("**/*.java")
//        googleJavaFormat()
//        indentWithSpaces(2)
//        trimTrailingWhitespace()
//        removeUnusedImports()
//    }
//}
