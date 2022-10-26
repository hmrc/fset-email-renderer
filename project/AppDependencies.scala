import sbt._

import play.sbt.PlayImport._

object AppDependencies {
  def apply()= Seq(
    ws,
    "uk.gov.hmrc"             %% "bootstrap-backend-play-28"    % "7.8.0",
    "uk.gov.hmrc"             %% "domain"                       % "8.1.0-play-28",
    "org.mockito"             %  "mockito-core"                 % "3.9.0"             % Test,
    "org.scalatestplus.play"  %% "scalatestplus-play"           % "5.1.0"             % "test,it",
    // Gives you access to MockitoSugar as it is no longer available in scalatestplus-play
    "org.scalatestplus"       %% "mockito-3-4"                  % "3.2.8.0"           % Test,
    "com.vladsch.flexmark"    %  "flexmark-all"                 % "0.36.8"            % "test,it"
  )
}
