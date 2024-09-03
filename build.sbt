ThisBuild / organization := "gov.cdc.hl7-pet"
ThisBuild / name := "hl7-pet"
ThisBuild / organizationName := "CDCgov"
ThisBuild / organizationHomepage := Some(url("https://github.com/cdcent/hl7-pet"))

ThisBuild / developers := List(
  Developer(
    id = "mcq1",
    name = "Marcelo Caldas",
    email = "mcq1@cdc.com",
    url = url("https://github.com/mscaldas2012")
  ),
  Developer(
    id = "xhu8",
    name = "Seun",
    email = "xuh8@cdc.gov",
    url = url("https://github.com/seun0925")
  )
)

ThisBuild / homepage := Some(url("https://github.com/cdcent/hl7-pet"))
ThisBuild / licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / description := "This project is a library to Parse HL7 v2 messages"

ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / scalaVersion := "2.13.13"

mainClass := Some("gov.cdc.hl7pet.DeIdentifierApp")
Global / excludeLintKeys += mainClass

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.14",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.14" % Test,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.17.0",
  "com.google.code.gson" % "gson" % "2.10.1"
)

crossPaths := true

ThisBuild / publishArtifact in (Compile, packageSrc) := true
ThisBuild / publishArtifact in Test := false

/*
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
*/

import xerial.sbt.Sonatype.sonatypeCentralHost

ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

// publishTo := sonatypePublishToBundle.value

ThisBuild / publishTo := Some(
  Resolver.sonatypeRepo("releases")
)

ThisBuild / publishMavenStyle := true

credentials += Credentials(
  "Sonatype Nexus Repository Manager",
  "oss.sonatype.org",
  sys.env.getOrElse("SONATYPE_USERNAME", ""),
  sys.env.getOrElse("SONATYPE_PASSWORD", "")
)
