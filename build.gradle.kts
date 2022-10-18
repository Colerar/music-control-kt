plugins {
  kotlin("jvm") version "1.7.20"
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.1stleg:jnativehook:2.1.0")
}

tasks.shadowJar {
  manifest {
    attributes["Main-Class"] = "MainKt"
  }
  exclude("META-INF/maven/**/*")
  exclude("**/*.kotlin_metadata")
  exclude("**/*.kotlin_builtins")
  exclude("**/*.kotlin_module")
  exclude("kotlin/UByte*")
  exclude("kotlin/UInt*")
  exclude("kotlin/UShort*")
  exclude("kotlin/ULong*")
  exclude("kotlin/jvm/internal/**/*Iterator*")
  exclude("kotlin/jvm/internal/**/*Reference*")
  exclude("kotlin/comparisons/**/*")
  exclude("kotlin/collections/unsigned/*")
  exclude("kotlin/collections/builders/*")
  exclude("kotlin/collections/**/*Sets*")
  exclude("kotlin/collections/**/*Maps*")
  exclude("kotlin/experimental/**/*")
  exclude("kotlin/coroutines/**/*")
  exclude("kotlin/math/**/*")
  exclude("kotlin/reflect/**/*")
  exclude("kotlin/random/**/*")
  exclude("kotlin/text/**/Regex*")
  exclude("kotlin/text/**/Match*")
  exclude("kotlin/time/**/*")
  exclude("kotlin/streams/**/*")
  exclude("org/jnativehook/example/**/*")
  exclude("org/jnativehook/lib/linux/**/*")
  exclude("org/jnativehook/lib/windows/**/*")
  exclude("org/jnativehook/lib/darwin/x86/**/*")
  minimize {
    exclude(dependency("com.1stleg:jnativehook:.*"))
  }
}
