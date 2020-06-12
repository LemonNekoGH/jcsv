import java.io.File

plugins {
    java
    kotlin("jvm") version "1.3.71"
    id("maven-publish")
}

val theVersion = "0.0.1-SNAPSHOT"
val repoDir = File(System.getProperty("user.home") + File.separator + "mavenRepo")

group = "moe.lemonneko"
version = theVersion

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = repoDir.toURI()
    }
}

publishing {
    repositories {
        maven {
            url = repoDir.toURI()
        }
        publications {
            publishing.publications.create("maven",MavenPublication::class.java){
                groupId = "moe.lemonneko"
                artifactId = "jcsv-ktx"
                version = theVersion
                from(components["java"])
            }
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("moe.lemonneko:jcsv:0.0.1-SNAPSHOT")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}