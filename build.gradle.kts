import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-noarg:1.3.71")
    }
}

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
    kotlin("plugin.jpa") version "1.3.71"
}

allprojects {

    group = "com.mark"
    version = "0.1"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.register<Node>() {
        workingDir = file("${project.projectDir}/frontend/webtoon")
        args = {"install"}
    }

}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")
        plugin("idea")
        plugin("eclipse")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("kotlin-allopen")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
}

node {
    version = '12.6.0'
    download = true
    workDir = file("${project.buildDir}/nodejs")
    npmWorkDir = file("${project.buildDir}/npm")
}

/*
def webappDir = "$projectDir/frontend/webtoon"

node {
    version = '12.6.0'
    download = true
    workDir = file("${project.buildDir}/nodejs")
    npmWorkDir = file("${project.buildDir}/npm")
}

task appNpmInstall(type: NpmTask) {
    workingDir = file("${project.projectDir}/frontend/webtoon")
    args = ["install"]
}

task yarnBuild(type: YarnTask) {
    workingDir = file("${project.projectDir}/frontend/webtoon")
    args = ['build']
}

task copyWebApp(type: Copy) {
    from "frontend/webtoon/build"
    into 'build/resources/main/static/.'
}

jacocoTestReport {
    reports {
        xml.enabled = true // coveralls plugin depends on xml format report
        html.enabled = true
    }
}

yarnBuild.dependsOn appNpmInstall
copyWebApp.dependsOn yarnBuild
compileJava.dependsOn copyWebApp
 */