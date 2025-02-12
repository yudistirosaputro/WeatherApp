
import com.android.build.gradle.TestExtension
import com.yudistiro.weather.configureKotlinAndroid
import com.yudistiro.weather.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
            }
            dependencies {
                add("testImplementation", libs.findBundle("mockk").get())
            }
        }


    }

}
