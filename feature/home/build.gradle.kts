plugins {
    id("weather.android.feature")
}
android {
    namespace = "com.yudistiro.weather.feature.home"
    buildFeatures.dataBinding = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
}