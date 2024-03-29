name:= "HL7-PET"

githubOwner := "cdcgov"
githubRepository := "HL7-PET"
githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")
=======

organization:= "gov.cdc.hl7"
organizationName:= "CDC"
scmInfo:= Some (
  ScmInfo(
    url("https://github.com/cdcent/hl7-pet"),
    "scm:git@github.com/cdcent/hl7-pet.git"
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

description := "this project is a library to Parse HL7 v2 messages"
licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/cdcent/hl7-pet"))

pomIncludeRepository := { _ => false }

//publishTo := Some("GitHub cdcgov Apache Maven Packages" at "https://maven.pkg.github.com/cdcgov/hl7-pet")
//credentials += Credentials(
//  "GitHub Package Registry",
//  "maven.pkg.github.com",
//  "cdcgov",
//  System.getenv("GITHUB_TOKEN")
//)
publishTo := {
  val nexus = "https://imagehub.cdc.gov/repository/maven-ede/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "/")
}
credentials += Credentials(Path.userHome / ".sbt" / ".credentials")


//credentials += Credentials ("~/.sbt/sonatype_credentials")
//credentials += Credentials ("CDC Nexus Repository Manager", "https://imagehub.cdc.gov/", "mcq1", "")

//publishTo := Some("GitHub cdcgov Apache Maven Packages" at "https://maven.pkg.github.com/cdcgov/hl7-pet")
//credentials += Credentials(
//  "GitHub Package Registry",
//  "maven.pkg.github.com",
//  "cdcgov",
//  System.getenv("GITHUB_TOKEN")
//)
publishMavenStyle := true

version := "1.2.9"
scalaVersion:= "2.13.10"

//mainClass in assembly := Some("gov.cdc.hl7pet.DeIdentifierApp")
mainClass := Some("gov.cdc.hl7pet.DeIdentifierApp")
Global / excludeLintKeys += mainClass

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.14" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-modules-base" % "2.14.0" pomOnly()
libraryDependencies += "com.google.code.gson" % "gson" % "2.10"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-modules-base" % "2.14.0" pomOnly()
libraryDependencies += "com.google.code.gson" % "gson" % "2.10"
//libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.14" % Test


//assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x => MergeStrategy.first
//}

crossPaths:= true

publishArtifact in (Compile, packageSrc) := true