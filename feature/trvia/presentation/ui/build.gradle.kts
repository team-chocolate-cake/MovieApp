plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.ui.trivia"
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    bases()
    triviaViewModel()
    coreDependencies()
    implementation(Dependencies.fragmentKtx)
    hilt()
    implementation(Dependencies.lottie)
    navigation()
    implementation(Dependencies.animatedProgressBar)
    implementation(Dependencies.circularProgress)
    implementation(Dependencies.annotation)
}
