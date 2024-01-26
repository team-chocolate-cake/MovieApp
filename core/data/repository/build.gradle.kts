plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.chocolatecake.repository"

    defaultConfig {
        buildConfigField("String", "IMAGE_BASE_PATH", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "TMDB_SIGNUP_URL", "\"https://www.themoviedb.org/signup\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    remote()
    local()
    usecase()
    homeUseCase()
    coreDependencies()
    implementation(Dependencies.retrofit)
    hilt()
    implementation(Dependencies.paging)
}