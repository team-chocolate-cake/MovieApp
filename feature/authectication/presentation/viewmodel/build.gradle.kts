plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.viewnodel"
}

dependencies {
    bases()
    authUseCase()
    coreDependencies()
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.lifecycleExtension)
    hilt()
    navigation()
    coroutine()
}