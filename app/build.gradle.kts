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
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chocolatecake.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    implementation(project(":core:bases"))
    implementation(project(":core:data:local"))
    implementation(project(":core:data:remote"))
    implementation(project(":core:data:repository"))
    implementation(project(":core:domain:usecase"))
    implementation(project(":feature:authectication:domain:usecase"))
    implementation(project(":feature:authectication:presentation:ui"))
    implementation(project(":feature:authectication:presentation:viewmodel"))
    implementation(project(":feature:home:domain:usecase"))
    implementation(project(":feature:home:presentation:home-ui"))
    implementation(project(":feature:home:presentation:viewmodel"))
    implementation(project(":feature:trvia:data:repository"))
    implementation(project(":feature:trvia:presentation:ui"))
    implementation(project(":feature:trvia:presentation:viewmodel"))
    implementation(project(":feature:trvia:domain:usecase"))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    // splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // room
    implementation("androidx.room:room-runtime:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
    implementation("androidx.room:room-ktx:2.5.1")
    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
}