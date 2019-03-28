import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("maven")
    id("jacoco")
    id("com.github.kt3k.coveralls") version "2.8.2"
}

group = "eu.df"
version = "1.0.0"

description = """Java implementation of the JiffyBox API"""

repositories {
    mavenCentral()
}

dependencies {
    compile("io.github.openfeign:feign-jackson:10.2.0")

    testCompile("com.github.tomakehurst:wiremock:2.22.0")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.5.0-M1")
    testCompile("org.slf4j:slf4j-log4j12:1.8.0-beta4")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.5.0-M1")
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
