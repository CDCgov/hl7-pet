
inThisBuild(
  List(
    organization := "gov.cdc",
    organizationName:= "CDC",
  )
)

// Repository for releases on Maven Central using Sonatype
publishTo := sonatypePublishToBundle.value
publishMavenStyle := true

sonatypeProfileName := "gov.cdc" // Your sonatype groupID
// Reference the project OSS repository

import xerial.sbt.Sonatype.GitHubHosting
sonatypeProjectHosting := Some(
  GitHubHosting(user = "mscaldas2012", repository = "hl7-pet", email = "mcq1@cdc.gov")
)

licenses:= Seq(
  "APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://cdc.gov"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/cdcgov/hl7-pet"),
    "scm:git@github.com/cdcgov/hl7-pet.git"
  )
)

sonatypeRepository := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) nexus + "content/repositories/snapshots"
  else nexus + "service/local"
}

import xerial.sbt.Sonatype.sonatypeCentralHost
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost
ThisBuild / sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
//credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")
//publishTo := Some("Maven Central" at "https://oss.sonatype.org/service/local/staging/deploy/maven2/")
credentials += Credentials(
 "Sonatype Nexus Repository Manager",
 "oss.sonatype.org",
 sys.env.getOrElse("SONATYPE_USERNAME", ""),
 sys.env.getOrElse("SONATYPE_PASSWORD", "")
)
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
 