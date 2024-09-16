import play.sbt.PlayImport.PlayKeys.playDefaultPort
import sbt.Keys.*
import sbt.*
import uk.gov.hmrc.DefaultBuildSettings
import uk.gov.hmrc.DefaultBuildSettings.{defaultSettings, scalaSettings}
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin

val appName = "fset-email-renderer"

lazy val appDependencies : Seq[ModuleID] = AppDependencies()
lazy val playSettings : Seq[Setting[?]] = Seq.empty

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / majorVersion := 0

lazy val microservice = Project(appName, file("."))
  .enablePlugins(PlayScala, SbtDistributablesPlugin)
  .settings(playSettings *)
  .settings(scalaSettings *)
  .settings(defaultSettings() *)
  .settings(playDefaultPort := 8960)
  .settings(
    libraryDependencies ++= appDependencies,
    retrieveManaged := true,
//    evictionWarningOptions in update := EvictionWarningOptions.default.withWarnScalaVersionEviction(false)
  )
  .settings(compileScalastyle := (Compile / scalastyle).toTask("").value,
    (Compile / compile) := ((Compile / compile) dependsOn compileScalastyle).value
  )
  .settings(
    resolvers += Resolver.jcenterRepo
  )

lazy val it = (project in file("it"))
  .enablePlugins(PlayScala)
  .dependsOn(microservice % "test->test") // the "test->test" allows reusing test code and test dependencies
  .settings(DefaultBuildSettings.itSettings())
  .settings(DefaultBuildSettings.addTestReportOption(Test, "int-test-reports"))
  .settings(Test / javaOptions += "-Dlogger.resource=logback-test.xml")
