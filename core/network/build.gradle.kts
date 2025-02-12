plugins {
    id("weather.android.library")
}

android {
    namespace = "com.yudis.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.coroutines.android)
    api(libs.bundles.network)
}