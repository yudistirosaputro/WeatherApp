plugins {
    id("weather.android.library")
}

android {
    namespace = "com.yudistiro.weather.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:local"))
    implementation(project(":domain"))
    implementation(project(":core:common"))
    implementation(libs.coroutines.android)
}