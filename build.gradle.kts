plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.eternalcode.plugintemplate"
version = "1.0.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://repo.mattstudios.me/artifactory/public/") }
    maven { url = uri("https://repo.panda-lang.org/releases") }
}

dependencies {
    // lombok
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    // annotations for plugins
    compileOnly("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    annotationProcessor("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")

    // paper api & kyori adventure
    compileOnly("io.papermc.paper:paper-api:1.19-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bukkit:4.1.0")
    implementation("net.kyori:adventure-text-minimessage:4.10.1")

    // LiteCommands
    implementation("dev.rollczi.litecommands:bukkit:1.8.4")

    // cdn configs
    implementation("net.dzikoysk:cdn:1.13.17")

    // expressible
    implementation("org.panda-lang:expressible:1.1.17")

    // tests
    testImplementation("io.papermc.paper:paper-api:1.19-R0.1-SNAPSHOT")
    testImplementation("org.codehaus.groovy:groovy-all:3.0.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("PluginTemplate v${project.version} (MC 1.17-1.18x).jar")

    exclude("org/intellij/lang/annotations/**")
    exclude("org/jetbrains/annotations/**")
    exclude("javax/**")

    relocate("net.dzikoysk", "com.eternalcode.plugintemplate.libs.net.dzikoysk")
    relocate("dev.rollczi", "com.eternalcode.plugintemplate.libs.dev.rollczi")
    relocate("org.panda_lang", "com.eternalcode.plugintemplate.libs.panda")
    relocate("panda", "com.eternalcode.plugintemplate.libs.panda")
    relocate("com.google.gson", "com.eternalcode.plugintemplate.libs.com.google.gson")
}
