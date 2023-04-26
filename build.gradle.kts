import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	id("java")
	id("maven-publish")
	id("jacoco")
	signing
	id("com.github.ben-manes.versions") version "0.46.0"
	id("com.github.nbaztec.coveralls-jacoco") version "1.2.15"
}

group = "io.github.jnodorp"
version = "1.0.1"

description = """Java implementation of the JiffyBox API"""

java {
	withJavadocJar()
	withSourcesJar()
}

publishing {
	repositories {
		maven {
			credentials {
				val sonatypeUsername: String? by project
				username = sonatypeUsername

				val sonatypePassword: String? by project
				password = sonatypePassword
			}

			url = if (version.toString().endsWith("-SNAPSHOT")) {
				uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
			} else {
				uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
			}
		}
	}

	publications {
		register<MavenPublication>("jiffybox") {
			from(components["java"])

			pom {
				licenses {
					license {
						name.set("MIT")
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
					connection.set("scm:git:https://github.com/jnodorp/jiffybox.git")
					developerConnection.set("scm:git:ssh://git@github.com:jnodorp/jiffybox.git")
					url.set("https://github.com/jnodorp/jiffybox/tree/main")
				}
			}
		}
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.github.openfeign:feign-jackson:12.2")

	testImplementation("com.github.tomakehurst:wiremock:2.27.2")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
	testImplementation("org.slf4j:slf4j-log4j12:2.0.6")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

signing {
	// Configure by setting the ORG_GRADLE_PROJECT_signingSecretKey environment variable.
	val signingSecretKey: String? by project

	// Configure by setting the ORG_GRADLE_PROJECT_signingPassword environment variable.
	val signingPassword: String? by project

	useInMemoryPgpKeys(signingSecretKey, signingPassword)
	sign(publishing.publications["jiffybox"])
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
			required.set(true)
		}
	}
}

tasks.withType<Javadoc> {
	setDestinationDir(file("$projectDir/docs"))

	(options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
}
