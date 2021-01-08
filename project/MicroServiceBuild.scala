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
    "uk.gov.hmrc"             %% "bootstrap-backend-play-26"    % "2.25.0",
    "uk.gov.hmrc"             %% "domain"                       % "5.10.0-play-26",
    "org.mockito"             %  "mockito-all"                  % "1.10.19" % "test",
    "uk.gov.hmrc"             %% "hmrctest"                     % "3.3.0" % "test,it",
    "com.typesafe.play"       %% "play-test"                    % PlayVersion.current % "test,it",
    "org.scalatestplus.play"  %% "scalatestplus-play"           % "3.1.3"       % "test,it"
  )
}
