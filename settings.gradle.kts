pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "VIsitorStatistic"
include(":app")
include(":core:data:dto")
include(":core:domain:models")
include(":core:data:network")
include(":feature:statistic:presentation")
include(":feature:statistic:di")
include(":core:data:di")
include(":feature:statistic:domain:api")
include(":feature:statistic:data:impl")
include(":uikit")
include(":core:resources")
include(":core:utils")
include(":core:presentation:utils")
include(":core:presentation:nav")
