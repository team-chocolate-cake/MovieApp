plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.viewmodel"
}

dependencies {
    bases()
    usecase()
    triviaUseCase()
    coreDependencies()
    hilt()
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.lifecycleExtension)
    coroutine()
}