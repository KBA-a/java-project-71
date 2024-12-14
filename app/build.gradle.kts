plugins {
    id("java")
    application
}
 // required

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "hexlet.code.App"
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    annotationProcessor("info.picocli:picocli-codegen:4.7.6")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    annotationProcessor("info.picocli:picocli-codegen:4.7.6")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.17.2")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.17.1")
}

tasks.test {
    useJUnitPlatform()
}