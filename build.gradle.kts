import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    id("com.gradleup.shadow") version "8.3.0"
}

group = "com.aethercane"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://repo.triumphteam.dev/snapshots/")
    }
}

dependencies {
    api("com.j256.ormlite:ormlite-jdbc:6.1")
    api("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.0")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("dev.triumphteam:triumph-gui:3.1.11")
    implementation("dev.triumphteam:triumph-cmd-bukkit:2.0.0-BETA-3")
}
tasks.withType<JavaCompile>(){
    options.encoding = "UTF-8"
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    archiveFileName.set(rootProject.name + "-" + rootProject.version + ".jar")
    mergeServiceFiles()


    relocate("com.fasterxml.jackson", "com.aethercane.libs.jackson")
    relocate("dev.triumphteam", "com.aethercane.libs.triumphteam")
}
java {
    targetCompatibility = JavaVersion.VERSION_21
    sourceCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}