plugins {
    id("weather.android.library")
    id("kotlin-kapt")
}

android {
    namespace = "com.yudistiro.weather.local"
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.coroutines.android)
}