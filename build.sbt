
import Common._

name := "demo-akka-websocket"

scalaVersion := "2.11.8"

version := "0.0.1"

lazy val commonSettings = Seq(
  version := "0.0.1",
  organization := "com.demo",
  scalaVersion := "2.11.8",
  test in assembly := {}
)


lazy val app = (project in file("app"))
  .settings(commonSettings: _*)
  .settings(
    mainClass in assembly := Some("com.demo.Boot"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
      "io.scalac" %% "reactive-rabbit" % reactiveRabbitVersion,
      "io.spray" %% "spray-routing" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "org.json4s" %% "json4s-native" % json4sVersion,
      "org.scalatest" %% "scalatest" % scalatestVersion % "test",
      "ch.qos.logback" % "logback-classic" % logbackVersion
    ),
    fork := true
  ).aggregate(producerLib, consumerLib).dependsOn(producerLib, consumerLib)


lazy val producerLib = (project in file("producer")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("com.demo.Boot"),
    assemblyJarName in assembly := "producer.jar",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
      "io.scalac" %% "reactive-rabbit" % reactiveRabbitVersion,
      "io.spray" %% "spray-routing" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "org.json4s" %% "json4s-native" % json4sVersion,
      "org.scalatest" %% "scalatest" % scalatestVersion % "test",
      "ch.qos.logback" % "logback-classic" % logbackVersion
    ),
    fork := true
    // more settings here ...
  )

lazy val consumerLib = (project in file("consumer")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("com.demo.Boot"),
    assemblyJarName in assembly := "consumer.jar",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
      "io.scalac" %% "reactive-rabbit" % reactiveRabbitVersion,
      "io.spray" %% "spray-routing" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "org.json4s" %% "json4s-native" % json4sVersion,
      "org.scalatest" %% "scalatest" % scalatestVersion % "test",
      "ch.qos.logback" % "logback-classic" % logbackVersion
    ),
    fork := true
    // more settings here ...
  )


lazy val customRepo = System.getProperty("maven.repo")

lazy val customRepository = (if (null != customRepo) customRepo else Path.userHome.absolutePath + "/.m2/repository")

publishMavenStyle := true
