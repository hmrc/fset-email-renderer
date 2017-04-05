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
    "uk.gov.hmrc"             %% "microservice-bootstrap"    % "5.14.0",
    "uk.gov.hmrc"             %% "play-authorisation"        % "4.2.0",
    "uk.gov.hmrc"             %% "play-health"               % "2.0.0",
    "uk.gov.hmrc"             %% "play-url-binders"          % "2.0.0",
    "uk.gov.hmrc"             %% "play-config"               % "3.0.0",
    "uk.gov.hmrc"             %% "logback-json-logger"       % "3.0.0",
    "uk.gov.hmrc"             %% "domain"                    % "4.0.0",
    "uk.gov.hmrc"             %% "hmrctest"                  % "2.0.0" % "test,it",
    "org.scalatest"           %% "scalatest"                 % "2.2.6" % "test,it",
    "org.pegdown"             %  "pegdown"                   % "1.6.0" % "test,it",
    "com.typesafe.play"       %% "play-test"                 % PlayVersion.current % "test,it",
    "org.scalatestplus.play"  %% "scalatestplus-play"        % "1.5.1" % "test,it"
  )
}
