inThisBuild(List(
  organization := "com.github.sbt",
  homepage := Some(url("https://github.com/sbt/sbt-ci-release")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "olafurpg",
      "Ólafur Páll Geirsson",
      "olafurpg@gmail.com",
      url("https://geirsson.com")
    )
  )
))

name:= "HL7-PET"

organization:= "gov.cdc.hl7"
organizationName:= "CDC"
scmInfo:= Some (
  ScmInfo(
    url("https://github.com/cdcgov/hl7-pet"),
    "scm:git@github.com/cdcgov/hl7-pet.git"
  )
)


pomIncludeRepository := { _ => false }

// version := "1.2.10"
// scalaVersion := "2.13.13"

mainClass := Some("gov.cdc.hl7pet.DeIdentifierApp")

// publishArtifact in (Compile, packageSrc) := true
