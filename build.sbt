import Dependencies._

lazy val root = (project in file("."))
  .settings(
    name := "fpl-data",
    homepage := Some(url("https://github.com/pdima11/fpl-data")),

    inThisBuild(List(
      organization := "com.pdima11",
      scalaVersion := "2.13.2",
    )),

    libraryDependencies ++= Seq(
      PureConfig.pureConfig
    ),
  )

