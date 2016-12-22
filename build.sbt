
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
    mainClass in assembly := Some("com.demo.Boot")
  ).aggregate(producerLib, consumerLib).dependsOn(producerLib, consumerLib)


lazy val producerLib = (project in file("producer")).
  settings(commonSettings: _*).
  settings(
    assemblyJarName in assembly := "producer.jar"
    // more settings here ...
  )

lazy val consumerLib = (project in file("consumer")).
  settings(commonSettings: _*).
  settings(
    assemblyJarName in assembly := "consumer.jar"
    // more settings here ...
  )


lazy val customRepo = System.getProperty("maven.repo")

lazy val customRepository = (if (null != customRepo) customRepo else Path.userHome.absolutePath + "/.m2/repository")

publishMavenStyle := true
