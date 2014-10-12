import play.Project._
name := "CV-Solutions"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository",
  "Typesafe" at "http://repo.typesafe.com/typesafe/releases",
  "fwbrasil.net" at "http://fwbrasil.net/maven/"
)

val activateVersion = "1.4.4"

libraryDependencies ++= Seq(
    jdbc,
    anorm,
    cache,
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
    "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
    "ws.securesocial" %% "securesocial" % "2.1.3",
    "org.squeryl" %% "squeryl" % "0.9.6-RC3"
)

val appDependencies = Seq(
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

play.Project.playScalaSettings
