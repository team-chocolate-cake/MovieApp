plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.bases"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    coreDependencies()
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.lottie)
    implementation(Dependencies.glide)
    implementation(Dependencies.splashScreen)
    implementation(Dependencies.paging)
    hilt()
}