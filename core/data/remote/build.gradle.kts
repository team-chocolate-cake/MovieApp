import java.util.Properties
plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

val localProperties = Properties()
localProperties.load(rootProject.file("local.properties").inputStream())

android {
    namespace = "com.chocolatecake.remote"

    defaultConfig {
        buildConfigField("String", "API_KEY", localProperties.getProperty("API_KEY"))
    }
    
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
}

dependencies {
    local()
    coreDependencies()
    retrofit()
    hilt()
}