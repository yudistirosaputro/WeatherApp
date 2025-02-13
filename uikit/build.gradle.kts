plugins {
    id("weather.android.feature")
}
android {
    namespace = "com.yudistiro.weather.uikit"
    buildFeatures.dataBinding = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
}