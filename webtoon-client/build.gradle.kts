tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
    baseName = "webtoon-client"
}

dependencies {
    implementation(project(":webtoon-domain"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jsoup:jsoup:1.11.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}