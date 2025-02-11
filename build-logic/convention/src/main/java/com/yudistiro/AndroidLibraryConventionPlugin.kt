package com.yudistiro

import com.android.build.gradle.LibraryExtension
import com.yudistiro.convention.configureKotlinAndroid
import com.yudistiro.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val customPropertiesFile = File(target.rootProject.projectDir, "custom.properties")
        val customProperties = Properties()

        customProperties.load(FileInputStream(customPropertiesFile))
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                    buildConfigField("String", "BASE_URL", customProperties.getProperty("BASE_URL"))
                    buildConfigField("String", "BASE_URL_GEO_CODE", customProperties.getProperty("BASE_URL_GEO_CODE"))
                    buildConfigField("String", "API_KEY", customProperties.getProperty("API_KEY"))

                }
                buildFeatures.buildConfig = true
            }
            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findBundle("mockk").get())
            }

        }
    }
}