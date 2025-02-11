package com.yudistiro

import com.yudistiro.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                 apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                "implementation"(libs.findLibrary("dagger.core").get())
                "kapt"(libs.findLibrary("dagger.compiler").get())
                "implementation"(libs.findLibrary("dagger.android").get())
                "kapt"(libs.findLibrary("dagger.android.compiler").get())
            }

        }
    }

}