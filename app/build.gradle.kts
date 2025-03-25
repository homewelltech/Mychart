plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    id("kotlin-kapt")  // Kotlin 注解处理器
    id("com.google.dagger.hilt.android")  // Hilt 插件
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.chatapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.chatapp"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.room.runtime)
//    kapt(libs.room.compiler)
    implementation(libs.room.ktx)


    implementation(libs.hilt.android)  // Hilt 依赖
    ksp(libs.hilt.android.compiler)   // Hilt 编译器（必须）


    // Room 组件
    implementation(libs.room.runtime)   // 运行时
    ksp(libs.androidx.room.compiler)          // 编译器（Kotlin 使用 kapt）
    implementation(libs.androidx.hilt.navigation.compose) // ✅ 需要这个依赖
    // 可选：使用 Kotlin 的协程支持
    implementation(libs.room.ktx)

    implementation(libs.coil.compose)
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.java.websocket)

}