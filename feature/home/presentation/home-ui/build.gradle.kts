plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.chocolatecake.ui.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    kapt {
        correctErrorTypes = true
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
    }
}

dependencies {

    implementation(project(":core:bases"))
    implementation(project(":feature:home:presentation:viewmodel"))
    implementation(project(":feature:trvia:presentation:ui"))
    implementation(project(":feature:authectication:presentation:ui"))
    implementation(project(":feature:home:domain:usecase"))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // viewModel
    implementation("androidx.fragment:fragment-ktx:1.6.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    // hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    // lottie
    implementation("com.airbnb.android:lottie:6.0.0")

    //glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.github.amoskorir:avatarimagegenerator:1.5.0")

    // recycler view decorator
    implementation("com.github.xabaras:RecyclerViewSwipeDecorator:1.4")

    // paging
    implementation("androidx.paging:paging-runtime:3.1.1")

    // youtube
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
   // implementation "com.pierfrancescosoffritti.androidyoutubeplayer:custom-ui:11.1.0"
 //   implementation "com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28"

    // refresh-layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}
