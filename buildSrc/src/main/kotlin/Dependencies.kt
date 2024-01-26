import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    // coreDependencies
    const val coreKtx = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val material = "com.google.android.material:material:${Versions.MATERIAL}"
    const val junit = "junit:junit:${Versions.JUNIT}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"

    // navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    // coroutine
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"

    // hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.HILT}"

    // splash
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.SPLASH}"

    // room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.ROOM}"

    // Preferences DataStore
    const val preferencesDatastore = "androidx.datastore:datastore-preferences:${Versions.PREFERENCE_DATASTORE}"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"

    // lottie
    const val lottie = "com.airbnb.android:lottie:${Versions.LOTTIE}"

    //glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val avatarImageGenerator = "com.github.amoskorir:avatarimagegenerator:${Versions.AVATAR_IMAGE_GENERATOR}"

    // recycler view decorator
    const val recyclerViewSwipeDecorator = "com.github.xabaras:RecyclerViewSwipeDecorator:${Versions.RECYCLE_VIEW_SWIPE}"

    // paging
    const val paging = "androidx.paging:paging-runtime:${Versions.PAGING}"

    // youtube
    const val CoreYoutubePlayer = "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.YOUTUBE_PLAYER}"
    const val CustomYoutubePlayer = "com.pierfrancescosoffritti.androidyoutubeplayer:custom-ui:${Versions.YOUTUBE_PLAYER}"
    const val chromecastSenderYoutubePlayer = "com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:${Versions.chromecastSenderYoutubePlayer}"

    // refresh-layout
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH}"

    /// viewModel
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtension}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"

    // Jetpack Compose dependencies
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeToolingPreviewAndroid = "androidx.compose.ui:ui-tooling-preview-android:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    const val animatedProgressBar = "com.github.mckrpk:AnimatedProgressBar:${Versions.animatedProgressBar}"

    const val circularProgress = "com.github.guilhe:circular-progress-view:${Versions.circularProgress}"

    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
}

fun DependencyHandler.coreDependencies(){
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidJunit)
    androidTestImplementation(Dependencies.espresso)
}

fun DependencyHandler.compose(){
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composeToolingPreview)
    implementation(Dependencies.composeToolingPreviewAndroid)
}

fun DependencyHandler.room(){
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.coroutine(){
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
}

fun DependencyHandler.retrofit(){
    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterGson)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.hilt(){
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.navigation(){
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
}

fun DependencyHandler.app(){
    implementation(project(":app"))
}
fun DependencyHandler.local(){
    implementation(project(":core:data:local"))
}
fun DependencyHandler.remote(){
    implementation(project(":core:data:remote"))
}
fun DependencyHandler.repository(){
    implementation(project(":core:data:repository"))
}
fun DependencyHandler.usecase(){
    implementation(project(":core:domain:usecase"))
}
fun DependencyHandler.entities(){
    implementation(project(":core:domain:entities"))
}
fun DependencyHandler.apiEntities(){
    api(project(":core:domain:entities"))
}
fun DependencyHandler.bases(){
    implementation(project(":core:bases"))
}
fun DependencyHandler.authUseCase(){
    implementation(project(":feature:authectication:domain:usecase"))
}
fun DependencyHandler.authViewModel(){
    implementation(project(":feature:authectication:presentation:viewmodel"))
}
fun DependencyHandler.authUi(){
    implementation(project(":feature:authectication:presentation:ui"))
}
fun DependencyHandler.homeUseCase(){
    implementation(project(":feature:home:domain:usecase"))
}
fun DependencyHandler.homeViewModel(){
    implementation(project(":feature:home:presentation:viewmodel"))
}
fun DependencyHandler.homeUi(){
    implementation(project(":feature:home:presentation:home-ui"))
}
fun DependencyHandler.triviaRepository(){
    implementation(project(":feature:trvia:data:repository"))
}
fun DependencyHandler.triviaUseCase(){
    implementation(project(":feature:trvia:domain:usecase"))
}
fun DependencyHandler.triviaUi(){
    implementation(project(":feature:trvia:presentation:ui"))
}
fun DependencyHandler.triviaViewModel(){
    implementation(project(":feature:trvia:presentation:viewmodel"))
}
fun DependencyHandler.apiTriviaEntities(){
    api(project(":feature:trvia:domain:entities"))
}