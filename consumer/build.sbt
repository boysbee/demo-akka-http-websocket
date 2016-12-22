name := "consumer"

version := "0.0.1"

scalaVersion := "2.11.8"

lazy val akkaVersion = "10.0.0"
lazy val reactiveRabbitVersion = "1.1.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaVersion,
  "io.scalac" %% "reactive-rabbit" % reactiveRabbitVersion
)
