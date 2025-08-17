plugins {
    java
    application
}

group = "com.cu5448"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring RestTemplate for REST client
    implementation("org.springframework:spring-web:6.2.0")
    implementation("org.springframework:spring-context:6.2.0")
    
    // Jackson for JSON processing
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.2")
    
    // HTTP client
    implementation("org.apache.httpcomponents.client5:httpclient5:5.4.0")
    
    // Lombok for reducing boilerplate
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("com.cu5448.pcb.client.ReportClientApplication")
}

tasks.withType<Test> {
    useJUnitPlatform()
}