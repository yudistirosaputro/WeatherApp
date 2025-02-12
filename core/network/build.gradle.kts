plugins {
    id("weather.android.library")
}

android {
    namespace = "com.yudis.weather.data"
}

dependencies {
    implementation(libs.coroutines.android)
    implementation(libs.bundles.network)
}