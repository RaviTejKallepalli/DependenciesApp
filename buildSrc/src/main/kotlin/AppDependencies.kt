import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // std lib
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    // android ui
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val material = "com.google.android.material:material:${Versions.material}"

    // test libs
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // dependency injection
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val hiltAnnotationProcessor = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val hiltLibraries = arrayListOf<String>().apply {
        add(hiltAndroid)
    }

    val hiltAnnotationProcessorLibraries = arrayListOf<String>().apply {
        add(hiltAnnotationProcessor)
    }
}