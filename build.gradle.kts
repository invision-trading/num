import net.ltgt.gradle.errorprone.CheckSeverity.WARN
import net.ltgt.gradle.errorprone.errorprone
import org.gradle.api.JavaVersion.VERSION_25
import org.jreleaser.model.Active.ALWAYS
import org.jreleaser.model.Active.NEVER

plugins {
    `java-library`
    id("io.freefair.lombok") version "9.2.0"
    id("net.ltgt.errorprone") version "5.1.0"
    `maven-publish`
    id("org.jreleaser") version "1.23.0"
}

group = "trade.invision"
version = "2.0.0"

java {
    sourceCompatibility = VERSION_25
    targetCompatibility = VERSION_25
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jspecify:jspecify:1.0.0")
    implementation("com.google.guava:guava:33.5.0-jre")
    implementation("ch.obermuhlner:big-math:2.3.2")

    errorprone("com.google.errorprone:error_prone_core:2.48.0")
    errorprone("com.uber.nullaway:nullaway:0.13.1")
    errorprone("net.jacobpeterson:final-coat:1.2.1")
}

tasks.withType(JavaCompile::class) {
    options.errorprone {
        allErrorsAsWarnings = true
        allSuggestionsAsWarnings = true
        disableWarningsInGeneratedCode = true

        disable("MissingSummary")
        disable("NullableOptional")
        check("Varifier", WARN)
        check("IdentifierName", WARN)
        check("MissingBraces", WARN)
        check("FieldCanBeFinal", WARN)
        check("MissingDefault", WARN)
        check("SwitchDefault", WARN)
        check("RedundantNullCheck", WARN)
        check("FieldMissingNullable", WARN)
        check("ParameterMissingNullable", WARN)
        check("ReturnMissingNullable", WARN)

        check("NullAway", WARN)
        option("NullAway:OnlyNullMarked", true)
        option("NullAway:JSpecifyMode", true)
        check("RequireExplicitNullMarking", WARN)

        check("FinalCoat", WARN)
    }
}

tasks.withType(Javadoc::class) {
    options {
        (this as StandardJavadocDocletOptions).addBooleanOption("Xdoclint:none", true)
        links = listOf(
                "https://docs.oracle.com/en/java/javase/${java.targetCompatibility.majorVersion}/docs/api/",
                "https://jspecify.dev/docs/api/",
                "https://errorprone.info/api/latest/")
    }
}

val jreleaserMavenRepositoryDirectory = rootProject.layout.buildDirectory.dir("jreleaser-staging")

publishing {
    publications.create("jreleaser", MavenPublication::class) {
        from(components["java"])
        pom {
            name = artifactId
            description = "A Java library that abstracts the mathematical operations on real decimal numbers " +
                    "represented in computer memory as floating-point binary numbers (`Double`) or " +
                    "arbitrary-precision decimal numbers (`BigDecimal`)."
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
                connection = pom.url.map { "scm:git:$it.git" }
                developerConnection = connection
                url = pom.url
            }
        }
    }
    repositories.maven {
        url = uri(jreleaserMavenRepositoryDirectory)
    }
}

jreleaser {
    signing {
        pgp {
            active = ALWAYS
            armored = true
        }
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(jreleaserMavenRepositoryDirectory.get())
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
