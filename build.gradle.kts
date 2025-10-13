plugins {
    `java-library`
    `maven-publish`
}

group = "org.slimecraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    api("io.github.classgraph:classgraph:4.8.173")
    api("fr.mrmicky:fastboard:2.1.5")
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.slimecraft"
            artifactId = "bedrock"
            version = "1.0-SNAPSHOT"

            from(components["java"])
        }
    }
}