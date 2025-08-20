import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.0"
}

group = "com.aethercane"
version = "1.6"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://repo.triumphteam.dev/snapshots/")
    }
    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

dependencies {
    implementation("com.j256.ormlite:ormlite-jdbc:6.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.0")
    implementation("dev.triumphteam:triumph-gui:3.1.11")
    implementation("dev.triumphteam:triumph-cmd-bukkit:2.0.0-BETA-3")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
}
tasks.withType<JavaCompile>(){
    options.encoding = "UTF-8"
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    archiveFileName.set(rootProject.name + "-" + rootProject.version + ".jar")
    mergeServiceFiles()
    manifest {
        attributes(
            "Main-Class" to "com.aethercane.aethercanelib.AetherCaneLib"
        )
    }
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
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}