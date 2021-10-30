import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("maven-publish")
    id("jacoco")
    id("com.github.kt3k.coveralls") version "2.12.0"
    id("com.github.ben-manes.versions") version "0.39.0"
}

group = "eu.df"
version = "1.0.1"

description = """Java implementation of the JiffyBox API"""

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/jnodorp/jiffybox")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])

            pom {
                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("jnodorp")
                        name.set("Julian Nodorp")
                        email.set("julian.nodorp@mailbox.org")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/jnodorp/jiffybox.git")
                    developerConnection.set("scm:git:ssh://git@github.com:jnodorp/jiffybox.git")
                    url.set("https://github.com/jnodorp/jiffybox")
                }
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.openfeign:feign-jackson:11.7")

    testImplementation("com.github.tomakehurst:wiremock:2.27.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.slf4j:slf4j-log4j12:2.0.0-alpha5")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
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
