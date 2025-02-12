plugins {
    id("weather.android.library")
}

android {
    namespace = "com.yudistiro.weather.domain"

}

dependencies {

    implementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)
}