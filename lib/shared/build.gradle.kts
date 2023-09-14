import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.incremental.createDirectory

plugins {
    kotlin("multiplatform")
}

version = "1.0-SNAPSHOT"

tasks.withType<KotlinJsCompile>().configureEach {
    kotlinOptions.moduleKind = "commonjs"
}

kotlin {
    jvm()

    js(IR) {
        nodejs {
            useCommonJs()
        }
    }

    sourceSets {
        val ktor_version: String by project

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                // requires "@js-joda/core" for jsMain
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(npm("@comunica/query-sparql", "2.6.9"))
                implementation(npm("n3", "1.16.4"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
            }
        }

    }
}
