name := "crawler"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += Resolver.sonatypeRepo("releases")

val akkaHttpVersion = "10.1.1"

libraryDependencies ++= Seq(
  "net.ruippeixotog" %% "scala-scraper" % "2.1.0",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "commons-validator" % "commons-validator" % "1.6",
  "io.swagger" % "swagger-jaxrs" % "1.5.19",
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.14.0",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "com.github.pureconfig" %% "pureconfig" % "0.9.1"
)