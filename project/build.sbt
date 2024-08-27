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

// publishTo := Some("Maven cdcgov Apache Maven Packages" at "https://central.sonatype.com/cdc/gov/")
pomIncludeRepository := { _ => false }

