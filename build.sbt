ThisBuild / organization := "io.sportal"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.6"

ThisBuild / organization := "io.sportal"

lazy val sportal = (project in file("."))
  .settings(name := "Sportal")

lazy val app = (project in file("app"))
  .settings(name := "app")