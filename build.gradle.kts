plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

//Versions
val commonsLang3Version = "3.12.0"
val lombokVersion = "1.18.20"
val moshiVersion = "1.12.0"
val restAssuredVersion = "4.4.0"
val junitVersion = "4.13.2"
val junitTestEngineVersion = "5.8.1"
val hamcrestVersion = "1.3"
val serenityVersion = "2.6.0"

dependencies {
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$junitTestEngineVersion")
    testImplementation("junit:junit:$junitVersion")
    testImplementation("net.serenity-bdd:serenity-core:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-junit:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-rest-assured:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-gradle-plugin:$serenityVersion")
    testImplementation("com.squareup.moshi:moshi:$moshiVersion")
    testImplementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:json-schema-validator:$restAssuredVersion")
    testImplementation("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.test {
    useJUnitPlatform()
}
