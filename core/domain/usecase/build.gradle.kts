plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.usecase"
}

dependencies {
    apiEntities()
    coreDependencies()
    hilt()
    implementation(Dependencies.paging)

}