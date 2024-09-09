
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
  GitHubHosting(user = "Marcelo Caldas", repository = "hl7-pet", email = "mcq1@cdc.gov")
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
ThisBuild / sonatypeRepository := "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
//credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credentials")
//publishTo := Some("Maven Central" at "https://oss.sonatype.org/service/local/staging/deploy/maven2/")
credentials += Credentials(
 "Sonatype Nexus Repository Manager",
 "oss.sonatype.org",
 sys.env.getOrElse("SONATYPE_USERNAME", ""),
 sys.env.getOrElse("SONATYPE_PASSWORD", "")
)
publishMavenStyle := true
