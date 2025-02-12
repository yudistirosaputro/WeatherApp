
import com.android.build.api.dsl.ApplicationExtension
import com.yudistiro.weather.configureKotlinAndroid
import com.yudistiro.weather.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("androidx.navigation.safeargs")
                apply("weather.android.dagger")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.apply {
                    targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                    versionCode = libs.findVersion("versionCode").get().toString().toInt()
                    versionName = libs.findVersion("versionName").get().toString()
                }
                buildFeatures.apply {
                    buildConfig = true
                    dataBinding = true
                }

            }
            dependencies {
                add("testImplementation", libs.findBundle("mockk").get())
            }
        }
    }

}