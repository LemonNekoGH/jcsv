import java.net.URI
import java.util.Properties
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
        url = URI.create("https://packages.aliyun.com/maven/repository/2008850-snapshot-0dxuho/")
    }
    maven {
        url = URI.create("https://packages.aliyun.com/maven/repository/2008850-release-0dxuho/")
    }
    maven {
        url = repoDir.toURI()
    }
}

publishing {
    repositories {
        maven {
            credentials {
                val prop = file("publish.properties")
                val p = Properties()
                prop.inputStream().apply {
                    p.load(this)
                }
                username = p["username"] as String
                password = p["password"] as String
            }
            val releaseUrl = "https://packages.aliyun.com/maven/repository/2008850-release-0dxuho/"
            val snapshotUrl = "https://packages.aliyun.com/maven/repository/2008850-snapshot-zqKbkn/"
            url = if (theVersion.endsWith("-SNAPSHOT")) {
                URI.create(snapshotUrl)
            } else {
                URI.create(releaseUrl)
            }
        }
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