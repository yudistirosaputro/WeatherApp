plugins {
    id("weather.android.library")
    id("kotlin-kapt")
}

android {
    namespace = "com.yudistiro.weather.local"
}

dependencies {
    api(libs.room.runtime)
    api(libs.room.ktx)
    implementation(project(":core:common"))
    kapt(libs.room.compiler)
    implementation(libs.coroutines.android)
}