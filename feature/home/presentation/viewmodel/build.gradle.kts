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
    homeUseCase()
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.lifecycleExtension)
    coreDependencies()
    hilt()
    coroutine()
    implementation(Dependencies.paging)
}