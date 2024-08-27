name:= "HL7-PET"

//githubOwner := "cdc.gov"
//githubRepository := "HL7-PET"
//githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")


organization:= "gov.cdc.hl7"
organizationName:= "CDC"
scmInfo:= Some (
  ScmInfo(
    url("https://github.com/CDCgov/hl7-pet"),
    "scm:git@github.com/CDCgov/hl7-pet.git"
  )
)

developers := List(
  Developer(
    id="mcq1",
    name="Marcelo Caldas",
    email = "mcq1@cdc.com",
    url = url ("https://github.com/mscaldas2012")
  ),
  Developer(
    id="xhu8",
    name="Seun",
    email="xuh8@cdc.gov",
    url=url("https://github.com/seun0925")
  )
)

description := "This project is a library to Parse HL7 v2 messages"
licenses := List("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/cdcent/hl7-pet"))

pomIncludeRepository := { _ => false }

scalaVersion:= "2.13.13"

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

crossPaths:= true

publishArtifact in (Compile, packageSrc) := true

resolvers in Global ++= Seq(
  "Sbt plugins"                   at "https://dl.bintray.com/sbt/sbt-plugin-releases",
  "Maven Central Server"          at "https://repo1.maven.org/maven2",
  "TypeSafe Repository Releases"  at "https://repo.typesafe.com/typesafe/releases/",
  "TypeSafe Repository Snapshots" at "https://repo.typesafe.com/typesafe/snapshots/"
)