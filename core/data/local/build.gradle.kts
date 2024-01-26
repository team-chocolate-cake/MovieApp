plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.local"
}

dependencies {
    coreDependencies()
    room()
    implementation(Dependencies.preferencesDatastore)
    implementation(Dependencies.coroutinesCore)
    hilt()
}