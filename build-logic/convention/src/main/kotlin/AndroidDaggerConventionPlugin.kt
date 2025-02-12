import com.yudistiro.weather.libs
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
               add( "implementation",(libs.findLibrary("dagger.core").get()))
               add( "kapt",(libs.findLibrary("dagger.compiler").get()))
               add("implementation",(libs.findLibrary("dagger.android").get()))
                add("implementation",(libs.findLibrary("dagger.android.support").get()))
               add("kapt",(libs.findLibrary("dagger.android.compiler").get()))
            }

        }
    }

}