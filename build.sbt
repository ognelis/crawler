name := "crawler"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += Resolver.sonatypeRepo("releases")

val akkaHttpVersion = "10.1.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "io.swagger" % "swagger-jaxrs" % "1.5.19",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.14.0",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalamock" %% "scalamock" % "3.6.0" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
)