import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("maven")
    id("jacoco")
    id("com.github.kt3k.coveralls") version "2.9.0"
}

group = "eu.df"
version = "1.0.0"

description = """Java implementation of the JiffyBox API"""

repositories {
    mavenCentral()
}

dependencies {
    compile("io.github.openfeign:feign-jackson:10.7.3")

    testCompile("com.github.tomakehurst:wiremock:2.25.1")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.6.0-RC1")
    testCompile("org.slf4j:slf4j-log4j12:2.0.0-alpha1")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.6.0-RC1")
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
