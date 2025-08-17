plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "7.0.0.BETA2"
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	
	// Lombok for reducing boilerplate code
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
	
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Spotless configuration for formatting and linting
spotless {
	java {
		// Target all Java files
		target("src/**/*.java")
		
		// Use Google Java Format with AOSP style (4-space indents)
		googleJavaFormat("1.22.0").aosp()
		
		// Import organization
		importOrder("java", "javax", "org", "com", "")
		
		// Basic formatting
		trimTrailingWhitespace()
		endWithNewline()
	}
}

// Configure build integration
tasks.named("check") {
	dependsOn("spotlessCheck")
}

// Pre-commit hook configuration
tasks.register("preCommit") {
	group = "verification"
	description = "Runs code quality checks before commit"
	dependsOn("spotlessCheck", "test")
}