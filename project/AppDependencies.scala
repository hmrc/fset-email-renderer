import sbt._

import play.sbt.PlayImport._

object AppDependencies {

  val bootstrapVersion = "8.6.0"

  def apply()= Seq(
    ws,
    "uk.gov.hmrc"   %% "bootstrap-backend-play-30"  % bootstrapVersion,
    "uk.gov.hmrc"   %% "bootstrap-test-play-30"     % bootstrapVersion    % "test"
  )
}
