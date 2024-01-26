plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.usecase"
}

dependencies {
    usecase()
    coreDependencies()
    hilt()
    implementation(Dependencies.paging)
}