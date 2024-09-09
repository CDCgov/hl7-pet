// Project name
name := "HL7-PET"

// Project version
version := "1.2.11"

// Scala version
scalaVersion := "2.13.13"

// Organization name (this will be used in the artifact coordinates)
organization := "gov.cdc"

// Credentials for publishing (make sure to replace with your actual credentials)
publishTo := Some("Maven Central" at "https://oss.sonatype.org/service/local/staging/deploy/maven2/")
credentials += Credentials(
 "Sonatype Nexus Repository Manager",
 "oss.sonatype.org",
 sys.env.getOrElse("SONATYPE_USERNAME", ""),
 sys.env.getOrElse("SONATYPE_PASSWORD", "")
)
publishMavenStyle := true

// Optional: Additional publishing settings
pomSettings := Seq(
  pomExtra :=
    <url>https://github.com/cdcgov/hl7-pet</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      </license>
    </licenses>
    <developers>
      <developer>
        <id>mcq1</id>
        <name>Marcelo Caldas</name>
        <url>https://github.com/cdcgov/hl7-pet</url>
      </developer>
    </developers>
)

// Optional: Set Scala version in the POM file (if needed)
pomSettings += pomExtra := (
  <scm>
    <url>https://github.com/cdcgov/hl7-pet</url>
    <connection>scm:git@github.com/cdcgov/hl7-pet.git</connection>
  </scm>
)

// Add plugin for SBT publishing (make sure to include this in project/plugins.sbt as well)
addSbtPlugin("com.typesafe.sbt" % "sbt-pom-reader" % "1.2.0")

mainClass := Some("gov.cdc.hl7pet.DeIdentifierApp")
Global / excludeLintKeys += mainClass

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.14" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.17.0"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-modules-base" % "2.17.0" pomOnly()
libraryDependencies += "com.google.code.gson" % "gson" % "2.10.1"

crossPaths:= true

publishArtifact in (Compile, packageSrc) := true