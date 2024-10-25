val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "3.0.0-rc-1"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Ktor test dependencies
    testImplementation("io.ktor:ktor-server-test-host-jvm")

    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3") // JUnit Jupiter API
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3") // JUnit Jupiter Engine

    // For Kotlin test support (optional, as you already have this)
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

// Enable JUnit Platform
tasks.test {
    useJUnitPlatform()
}
