import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("maven-publish")
    id("jacoco")
    id("com.github.kt3k.coveralls") version "2.10.1"
    id("com.github.ben-manes.versions") version "0.28.0"
}

group = "eu.df"
version = "1.0.0"

description = """Java implementation of the JiffyBox API"""

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.openfeign:feign-jackson:11.0")

    testImplementation("com.github.tomakehurst:wiremock:2.26.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0-M1")
    testImplementation("org.slf4j:slf4j-log4j12:2.0.0-alpha1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0-M1")
}

tasks.withType<Jar> {
    manifest {
        attributes["Automatic-Module-Name"] = "${project.group}.${project.name}"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}

tasks.withType<JacocoReport> {
    reports {
        xml.apply {
            isEnabled = true
        }

        html.apply {
            isEnabled = true
        }
    }
}

tasks.withType<Javadoc> {
    setDestinationDir(file("$projectDir/docs"))

    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
}
