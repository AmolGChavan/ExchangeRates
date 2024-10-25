import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.hilt)
    alias(libs.plugins.kotlin.parcalize)
}

val properties = Properties().apply { load(rootProject.file("local.properties").reader()) }

android {
    namespace = "com.paypay.exchangerates"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.paypay.exchangerates"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.paypay.exchangerates.CustomTestRunner"

        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }


    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
            )
        )
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Hilt Setup
    implementation(libs.androidx.hilt)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation)

    //coroutine
    implementation(libs.androidx.kotlinx.coroutines)
    implementation(libs.androidx.kotlinx.coroutines.android)
  
    //Splash-Screen
    implementation(libs.androidx.splash)

    //Data Store
    implementation(libs.androidx.datastore)


    //Retrofit
    implementation(libs.androidx.retrofit)
    implementation(libs.androidx.retrofit.converter.gson)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Accompanist
    implementation(libs.androidx.systemuicontroller)

    //Constraint-layout
    implementation(libs.androidx.constraintlayout.compose)

    //Timber
    implementation(libs.timber)


    //Test Setup
    testImplementation(libs.junit)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.android.test.room.testing)
    testImplementation(libs.android.hilt.android.testing)
    testImplementation(libs.android.test.core)
    testImplementation(libs.test.mockk)
    testImplementation(libs.android.test.jccoco)
    testImplementation(libs.android.core.testing)
    kspTest(libs.androidx.hilt.compiler)


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.android.test.room.testing)
    androidTestImplementation(libs.android.test.core)
    androidTestImplementation(libs.test.mockk)
    androidTestImplementation(libs.test.coroutines)
    androidTestImplementation(libs.android.test.jccoco)
    androidTestImplementation(libs.android.core.testing)
    androidTestImplementation(libs.android.test.ui)

    androidTestImplementation(libs.android.hilt.android.testing)
    kspAndroidTest(libs.androidx.hilt.compiler)



    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}