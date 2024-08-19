inThisBuild(
  List(
    organization := "com.github.sbt",
    homepage := Some(url("https://github.com/olafurpg/sbt-ci-release")),
    licenses := Seq(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "olafurpg",
        "Ólafur Páll Geirsson",
        "olafurpg@gmail.com",
        url("https://geirsson.com")
      )
    )
  )
)

import xerial.sbt.Sonatype.sonatypeCentralHost

ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

name:= "HL7-PET"

//githubOwner := "cdc.gov"
//githubRepository := "HL7-PET"
//githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")


organization:= "gov.cdc.hl7"
organizationName:= "CDC"
scmInfo:= Some (
  ScmInfo(
    url("https://github.com/cdcgov/hl7-pet"),
    "scm:git@github.com/cdcgov/hl7-pet.git"
  )
)

developers := List(
  Developer(
    id="mcq1",
    name="Marcelo Caldas",
    email = "mcq1@cdc.com",
    url = url ("https://github.com/cdcent/hl7-pet")
  )
)

description := "This project is a library to Parse HL7 v2 messages"
licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/cdcent/hl7-pet"))

pomIncludeRepository := { _ => false }

// publishTo := Some("GitHub cdcgov Apache Maven Packages" at "https://maven.pkg.github.com/cdcgov/hl7-pet")
// credentials += Credentials(
//   "GitHub Package Registry",
//   "maven.pkg.github.com",
//   "cdcgov",
//   System.getenv("GITHUB_TOKEN")
// )

// credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

// publishMavenStyle := true

// version := "1.2.10"
// scalaVersion:= "2.13.10"

// version := "1.2.10"
scalaVersion := "2.13.13"

//mainClass in assembly := Some("gov.cdc.hl7pet.DeIdentifierApp")
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

//assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x => MergeStrategy.first
//}

// crossPaths:= true

// publishArtifact in (Compile, packageSrc) := true

lazy val plugin = project
  .enablePlugins(SbtPlugin)
  .settings(
    moduleName := "sbt-ci-release",
    pluginCrossBuild / sbtVersion := "1.0.4",
    addSbtPlugin("com.github.sbt" % "sbt-dynver" % "5.0.1"),
    addSbtPlugin("com.github.sbt" % "sbt-git" % "2.0.1"),
    addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.2.1"),
    addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.11.2")
  )
