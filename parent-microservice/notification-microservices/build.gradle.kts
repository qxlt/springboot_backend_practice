plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "ca.gbc"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	compileOnly("org.projectlombok:lombok")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:kafka")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.apache.avro:avro:1.12.0")
	implementation("io.confluent:kafka-schema-registry-client:7.7.1")
	implementation("io.confluent:kafka-avro-serializer:7.7.1")
//	implementation(project(":shared-schema"))
	runtimeOnly("io.confluent:kafka-avro-serializer:7.7.1")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
