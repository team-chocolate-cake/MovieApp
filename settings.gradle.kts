pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "MovieApp"
include(":app")
include(":core:data:local")
include(":core:data:remote")
include(":core:data:repository")
include(":core:domain:usecase")
include(":core:domain:entities")
include(":core:bases")
include(":feature:authectication:domain:usecase")
include(":feature:authectication:presentation:viewmodel")
include(":feature:authectication:presentation:ui")
include(":feature:home:domain:usecase")
include(":feature:home:presentation:viewmodel")
include(":feature:home:presentation:home-ui")
include(":feature:trvia:data:repository")
include(":feature:trvia:domain:usecase")
include(":feature:trvia:presentation:ui")
include(":feature:trvia:presentation:viewmodel")
include(":feature:trvia:domain:entities")
