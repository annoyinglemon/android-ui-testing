import java.net.URI

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.androidGradle)
        classpath(Plugins.kotlinGradle)
        classpath(Plugins.navSafeArgs)
        classpath(Plugins.googleServices)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI("https://jitpack.io")
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}