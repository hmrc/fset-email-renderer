import sbt._

import play.sbt.PlayImport._
import play.core.PlayVersion

object AppDependencies {
  def apply()= Seq(
    ws,
    "uk.gov.hmrc"             %% "bootstrap-backend-play-27"    % "2.25.0",
    "uk.gov.hmrc"             %% "domain"                       % "5.10.0-play-27",
    "org.mockito"             %  "mockito-all"                  % "1.10.19"           % "test",
    "uk.gov.hmrc"             %% "hmrctest"                     % "3.10.0-play-26"    % "test,it",
    "com.typesafe.play"       %% "play-test"                    % PlayVersion.current % "test,it",
    "org.scalatestplus.play"  %% "scalatestplus-play"           % "4.0.3"             % "test,it"
  )
}
