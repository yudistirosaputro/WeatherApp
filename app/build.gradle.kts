plugins {
    id("weather.android.application")
}

android {
    namespace = "com.yudistiro.weather"
    buildTypes {
        release {

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    //module
//    implementation(project(":core:network"))
//    implementation(project(":core:local"))
//    implementation(project(":core:data"))
    implementation(project(":core:common"))
//    implementation(project(":domain"))
//    implementation(project(":feature:home"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}