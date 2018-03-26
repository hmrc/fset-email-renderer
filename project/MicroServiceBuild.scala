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
    "uk.gov.hmrc"             %% "microservice-bootstrap"    % "6.13.0",
    "uk.gov.hmrc"             %% "play-url-binders"          % "2.1.0",
    "uk.gov.hmrc"             %% "domain"                    % "5.0.0",
    "uk.gov.hmrc"             %% "hmrctest"                  % "3.0.0" % "test,it",
//    "org.scalatest"           %% "scalatest"                 % "2.2.6" % "test,it",
    "org.pegdown"             %  "pegdown"                   % "1.6.0" % "test,it",
    "com.typesafe.play"       %% "play-test"                 % PlayVersion.current % "test,it",
    "org.scalatestplus.play"  %% "scalatestplus-play"        % "1.5.1" % "test,it"
  )
}
