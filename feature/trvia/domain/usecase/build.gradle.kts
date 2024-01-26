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
    apiTriviaEntities()
    coreDependencies()
    hilt()
}