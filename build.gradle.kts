buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    }
}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false

    // https://github.com/JLLeitschuh/ktlint-gradle
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.3.2"
}

subprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("org.jlleitschuh.gradle.ktlint-idea")
    }

    tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask> {
        workerMaxHeapSize.set("512m")
    }

    ktlint {
        android.set(true)
        outputColorName.set("RED")
        outputToConsole.set(true)
        debug.set(false)
        verbose.set(true)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
