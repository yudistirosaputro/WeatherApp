plugins {
    id("weather.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "com.yudistiro.weather.domain"

}

dependencies {

    implementation(libs.coroutines.android)
    implementation(project(":core:common"))
    testImplementation(libs.coroutines.test)
}