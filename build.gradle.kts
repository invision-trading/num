import org.jreleaser.model.Active.ALWAYS
import org.jreleaser.model.Active.NEVER

plugins {
    `java-library`
    `maven-publish`
    id("org.jreleaser") version "1.17.0"
}

group = "trade.invision"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Jetbrains Annotations
    implementation("org.jetbrains", "annotations", "24.1.0")

    // big-math
    implementation("ch.obermuhlner", "big-math", "2.3.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    withJavadocJar()
    withSourcesJar()
}

tasks.javadoc.configure {
    options {
        (this as CoreJavadocOptions).addBooleanOption("Xdoclint:none", true)
        addStringOption("link", "https://docs.oracle.com/en/java/javase/21/docs/api/")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "num"
            from(components["java"])
            pom {
                name = "num"
                description = "A Java library that abstracts the mathematical operations on real decimal numbers " +
                        "represented in computer memory as floating-point binary numbers or " +
                        "arbitrary-precision decimal numbers."
                url = "https://github.com/invision-trading/num"
                inceptionYear = "2025"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                developers {
                    developer {
                        id = "Petersoj"
                        name = "Jacob Peterson"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/invision-trading/num.git"
                    developerConnection = "scm:git:ssh://github.com/invision-trading/num.git"
                    url = "https://github.com/invision-trading/num"
                }
            }
        }
    }
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

jreleaser {
    signing {
        active = ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                    // Timeout of 1 hour.
                    maxRetries = 60
                    retryDelay = 60
                }
            }
        }
    }
    release {
        github {
            uploadAssets = NEVER
        }
    }
}
