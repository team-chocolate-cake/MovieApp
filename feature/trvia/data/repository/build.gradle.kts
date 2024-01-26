plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.repository"
}

dependencies {
    remote()
    local()
    repository()
    usecase()
    triviaUseCase()
    coreDependencies()
    hilt()
    implementation(Dependencies.retrofit)
}