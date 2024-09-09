inThisBuild(
  List(
    name:= "HL7-PET",
    organization := "gov.cdc",
    organizationName:= "CDC",
    homepage := Some(url("https://github.com/cdcgov/hl7-pet")),
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
        url = url ("https://github.com/cdcgov/hl7-pet")
      )
    )
  )
)
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"
ThisBuild / sonatypeRepository := "https://oss.sonatype.org/service/local/staging/deploy/maven2/"

publishMavenStyle := true

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

Compile / packageSrc / publishArtifact := true

onLoadMessage := s"Welcome to sbt-ci-release ${version.value}"

lazy val plugin = project
  .enablePlugins(SbtPlugin)
  .settings(
    moduleName := "sbt-ci-release",
    pluginCrossBuild / sbtVersion := "1.0.4",
    addSbtPlugin("com.github.sbt" % "sbt-dynver" % "5.0.1"),
    addSbtPlugin("com.github.sbt" % "sbt-git" % "2.0.1"),
    addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.2.1"),
    addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.11.3")
  )