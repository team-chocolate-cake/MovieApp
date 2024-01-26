plugins {
    `android-library`
    `kotlin-android`
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.ui.home"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    bases()
    homeViewModel()
    triviaUi()
    authUi()
    homeUseCase()
    coreDependencies()
    implementation(Dependencies.fragmentKtx)
    navigation()
    hilt()
    implementation(Dependencies.lottie)
    implementation(Dependencies.glide)
    implementation(Dependencies.avatarImageGenerator)
    implementation(Dependencies.recyclerViewSwipeDecorator)
    implementation(Dependencies.paging)
    implementation(Dependencies.CoreYoutubePlayer)
    implementation(Dependencies.swipeRefreshLayout)
}
