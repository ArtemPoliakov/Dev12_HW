plugins {
    id("java")
    id ("org.flywaydb.flyway") version "10.0.0"
}

group = "com.homework"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val log4j2Version = "2.22.1"
val lombokVersion = "1.18.30"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    implementation("org.hibernate.orm:hibernate-core:6.4.2.Final")
    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    implementation("org.flywaydb:flyway-core:10.4.1")
    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
//    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
//    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
//    implementation("org.apache.logging.log4j:log4j-api:$log4j2Version")
//    // https://mvnrepository.com/artifact/com.h2database/h2
    testImplementation("com.h2database:h2:2.2.220")
}

flyway {
    cleanDisabled = false
    url="jdbc:h2:./data/database"
    user="Artem"
    password="very_strong_password"
    baselineOnMigrate = true
}

tasks.test {
    useJUnitPlatform()
}