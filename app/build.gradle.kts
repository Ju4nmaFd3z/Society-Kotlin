import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    // Activa el plugin del compilador que genera el código de @Serializable
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp) // Sobre hilt
    alias(libs.plugins.hilt) // Dagger sobre hilt
}

// Lee local.properties, que no se sube al repositorio y es donde cada uno puede cambiar la URL de la API
val propiedadesLocales = Properties().apply {
    val archivo = rootProject.file("local.properties")
    if (archivo.exists()) archivo.inputStream().use { load(it) }
}

android {
    namespace = "com.alanturing.society"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.alanturing.society"
        minSdk = 31
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // URL base de la API. Si local.properties no define API_BASE, usa la API desplegada en Vercel.
        // Termina en barra para que Retrofit sume bien las rutas.
        val apiBase = propiedadesLocales.getProperty("API_BASE", "https://society-api-xi.vercel.app/api/v1/")
        buildConfigField("String", "API_BASE", "\"$apiBase\"")
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true // Genera BuildConfig, donde está API_BASE
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.coil.compose) // Dependencia de coil para mostrar imágenes de internet.
    implementation(libs.androidx.navigation.compose) // Navigation compose
    implementation(libs.kotlinx.serialization.json) // Formato JSON, Navigation lo utiliza para guardar datos de rutas
    implementation(libs.hilt.android) // hilt
    ksp(libs.hilt.compiler) // hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.retrofit) // Llamadas a la API
    implementation(libs.retrofit.converter.gson) // JSON a clases de Kotlin con Gson
}