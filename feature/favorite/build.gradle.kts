plugins {
    id("weather.android.feature")
}
android {
    namespace = "com.yudistiro.weather.feature.favorite"
    buildFeatures.dataBinding = true
}

dependencies {

    implementation(project(":uikit"))
    implementation(project(":core:common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
}