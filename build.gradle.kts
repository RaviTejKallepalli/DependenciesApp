// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
    }
}

plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.5.30" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}