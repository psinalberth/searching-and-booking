plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.github.psinalberth"
version = "1.0.0"

val mapstructVersion by extra("1.6.3")
val kafkaVersion by extra("3.3.5")
val springDocVersion by extra("2.8.6")

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    implementation("org.springframework.kafka:spring-kafka:$kafkaVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.plusAssign(listOf(
        "-Amapstruct.suppressGeneratorTimestamp=true",
        "-Amapstruct.suppressGeneratorVersionInfoComment=true",
        "-Amapstruct.verbose=true"
    ))
}

tasks.withType<Test> {
    useJUnitPlatform()
}