import sbt.Keys._

object Common {
  val commonSettings = Seq(
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")
  )

  val akkaHttpVersion = "10.0.0"
  //  val akkaStreamVersion = "1.0-M3"
  val akkaStreamVersion = "2.4.14"
  val akkaVersion = "2.4.14"
  val reactiveRabbitVersion = "1.1.4"
  val sprayVersion = "1.3.1"
  val json4sVersion = "3.2.11"
  val logbackVersion = "1.1.8"
  val scalatestVersion = "3.0.0"
}
