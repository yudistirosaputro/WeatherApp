plugins {
    id("weather.android.feature")
}
android {
    namespace = "com.yudistiro.weather.feature.home"
    buildFeatures.dataBinding = true
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":uikit"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.androidx.swiperefreshlayout)
}