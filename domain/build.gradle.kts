plugins {
    id("weather.android.library")
}

android {
    namespace = "com.yudistiro.weather.domain"

}

dependencies {

    implementation(libs.coroutines.android)
    implementation(project(":core:common"))
    testImplementation(libs.coroutines.test)
}