plugins {
    id("org.jetbrains.intellij.platform") version "2.2.1"
}

group   = "com.github.darkpurpleislands"
version = "1.0.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        // Build against IntelliJ IDEA Community 2024.3
        intellijIdeaCommunity("2024.3")
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            // Islands UX requires 2024.1+; no upper bound so the theme works on future IDEs
            sinceBuild = "241"
            untilBuild = provider { null }
        }
    }
}

// Resources live at the project root under resources/, not src/main/resources/
sourceSets {
    main {
        resources.srcDirs("resources")
    }
}
