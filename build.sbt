import play.sbt.PlayImport.PlayKeys.playDefaultPort
import sbt.Keys._
import sbt.Tests.{Group, SubProcess}
import sbt._
import uk.gov.hmrc.DefaultBuildSettings.{addTestReportOption, defaultSettings, scalaSettings}
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin._
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
import uk.gov.hmrc.SbtAutoBuildPlugin

val appName = "fset-email-renderer"

lazy val appDependencies : Seq[ModuleID] = AppDependencies()
lazy val plugins : Seq[Plugins] = Seq.empty
lazy val playSettings : Seq[Setting[_]] = Seq.empty

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

lazy val microservice = Project(appName, file("."))
  .enablePlugins(Seq(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtDistributablesPlugin) ++ plugins : _*)
  .settings(majorVersion := 0)
  .settings(playSettings : _*)
  .settings(scalaSettings: _*)
  .settings(publishingSettings: _*)
  .settings(defaultSettings(): _*)
  .settings(playDefaultPort := 8960)
  .settings(
    scalaVersion := "2.13.8",
    libraryDependencies ++= appDependencies,
    retrieveManaged := true,
//    evictionWarningOptions in update := EvictionWarningOptions.default.withWarnScalaVersionEviction(false)
  )
  .configs(IntegrationTest)
  .settings(inConfig(IntegrationTest)(sbt.Defaults.itSettings): _*)
  .settings(
    IntegrationTest / Keys.fork := true,
    IntegrationTest / javaOptions += "-Dlogger.resource=logback-test.xml",
    IntegrationTest / unmanagedSourceDirectories := Seq((IntegrationTest / baseDirectory).value / "it"),
    addTestReportOption(IntegrationTest, "int-test-reports"),
    IntegrationTest / testGrouping := oneForkedJvmPerTest((IntegrationTest / definedTests).value),
    IntegrationTest / parallelExecution := false
  )
  .settings(compileScalastyle := (Compile / scalastyle).toTask("").value,
    (Compile / compile) := ((Compile / compile) dependsOn compileScalastyle).value
  )
  .settings(
    resolvers += Resolver.jcenterRepo
  )

def oneForkedJvmPerTest(tests: Seq[TestDefinition]): Seq[Group] =
  tests map { test =>
    val forkOptions = ForkOptions().withRunJVMOptions(Vector("-Dtest.name=" + test.name))
    Group(test.name, Seq(test), SubProcess(config = forkOptions))
  }
