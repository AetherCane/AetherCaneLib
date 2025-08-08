import org.gradle.internal.impldep.org.bouncycastle.util.encoders.UTF8

plugins {
    `java-library`
}

group = "com.aethercane"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    api("com.j256.ormlite:ormlite-jdbc:6.1")
    api("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.0")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

java.sourceCompatibility = JavaVersion.VERSION_21

tasks.withType<JavaCompile>(){
    options.encoding = "UTF-8"
}