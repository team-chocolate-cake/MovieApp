import java.util.Properties
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

val localProperties = Properties()
val localPropsFile = localProperties.load(rootProject.file("local.properties").inputStream())

android {
    namespace = "com.chocolatecake.movieapp"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {
    bases()
    local()
    remote()
    repository()
    usecase()
    authUseCase()
    authUi()
    authViewModel()
    homeUseCase()
    homeUi()
    homeViewModel()
    triviaRepository()
    triviaUi()
    triviaViewModel()
    triviaUseCase()

    coreDependencies()
    navigation()
    coroutine()
    hilt()
    room()
    retrofit()
    implementation(Dependencies.splashScreen)
    implementation(Dependencies.preferencesDatastore)
}