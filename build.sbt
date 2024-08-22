inThisBuild(List(
  name:= "HL7-PET",
  organization := "gov.cdc.hl7",
  organizationName:= "CDC",
  homepage := Some(url("https://github.com/cdcent/hl7-pet")),
  description := "This project is a library to Parse HL7 v2 messages",
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  scmInfo:= Some (
    ScmInfo(
      url("https://github.com/cdcgov/hl7-pet"),
      "scm:git@github.com/cdcgov/hl7-pet.git"
    )
  ),
  developers := List(
    Developer(
      id="mcq1",
      name="Marcelo Caldas",
      email = "mcq1@cdc.com",
      url = url ("https://github.com/cdcent/hl7-pet")
    )
  )
))

pomIncludeRepository := { _ => false }
// publishTo := Some("GitHub cdcgov Apache Maven Packages" at "https://maven.pkg.github.com/cdcgov/hl7-pet")

publishMavenStyle := true

version := "1.2.10"
scalaVersion := "2.13.13"

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
