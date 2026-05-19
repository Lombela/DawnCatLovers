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
    }
}

rootProject.name = "DawnCatLovers"

include(":app")
include(":core:model")
include(":core:domain")
include(":core:database")
include(":core:network")
include(":core:data")
include(":core:designsystem")
include(":feature:breeds")
