plugins {
    `java-library`
}

group = "trade.invision"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Jetbrains Annotations
    implementation("org.jetbrains", "annotations", "24.1.0")

    // Google Guava
    implementation("com.google.guava", "guava", "33.2.1-jre")

    // big-math
    implementation("ch.obermuhlner", "big-math", "2.3.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
