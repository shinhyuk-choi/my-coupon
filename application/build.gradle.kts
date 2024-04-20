dependencies {
    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis:3.2.4")

    // mysql
    api("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
    implementation("mysql:mysql-connector-java:8.0.33")

    // kafka
    api("org.springframework.kafka:spring-kafka:3.1.0")

    api(project(":common"))
}