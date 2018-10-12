import sbt._

import play.sbt.PlayImport._
import play.core.PlayVersion

object MicroServiceBuild extends Build with MicroService {

  val appName = "fset-email-renderer"

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
  def apply()= Seq(
    ws,
    "uk.gov.hmrc"             %% "microservice-bootstrap"    % "8.3.0",
    "uk.gov.hmrc"             %% "domain"                    % "5.2.0",
    "uk.gov.hmrc"             %% "hmrctest"                  % "3.1.0" % "test,it",
    "com.typesafe.play"       %% "play-test"                 % PlayVersion.current % "test,it",
    "org.scalatestplus.play"  %% "scalatestplus-play"        % "2.0.0" % "test,it"
  )
}
