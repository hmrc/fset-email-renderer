import sbt._

import play.sbt.PlayImport._

object AppDependencies {

  val bootstrapVersion = "10.1.0"

  def apply()= Seq(
    ws,
    "uk.gov.hmrc"           %% "bootstrap-backend-play-30"  % bootstrapVersion,
    "com.github.pureconfig" %% "pureconfig-generic-scala3"  % "0.17.8",
    "uk.gov.hmrc"           %% "bootstrap-test-play-30"     % bootstrapVersion    % "test"
  )
}
