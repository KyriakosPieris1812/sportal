val http4sVersion = "0.23.6"

libraryDependencies ++= Seq(
  "com.monovore" %% "decline" % "2.2.0",
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.typelevel" %% "cats-effect" % "3.2.9",
  "com.monovore" %% "decline-effect" % "2.1.0",
  "co.fs2" %% "fs2-core" % "3.2.2",
  "com.kubukoz" %% "sup-core" % "0.9.0-M6"
)