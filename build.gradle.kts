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
val testNgVersion = "7.4.0"
val hamcrestVersion = "1.3"
val serenityVersion = "2.6.0"

dependencies {
    implementation("org.testng:testng:$testNgVersion")
    implementation("net.serenity-bdd.serenity-gradle-plugin:$serenityVersion")
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-schema-validator:$restAssuredVersion")
    implementation("org.hamcrest:hamcrest-all:$hamcrestVersion")
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    implementation("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.testng:testng:$testNgVersion")
    testImplementation("org.apache.commons:commons-lang3:$commonsLang3Version")
    testImplementation("org.projectlombok:lombok:$lombokVersion")
    testImplementation("com.squareup.moshi:moshi:$moshiVersion")
    testImplementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.test {
    useTestNG()
}
