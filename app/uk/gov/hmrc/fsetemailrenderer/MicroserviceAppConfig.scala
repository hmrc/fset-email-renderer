/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.fsetemailrenderer

import javax.inject.{Inject, Singleton}
import play.api.{Configuration, Environment}
import pureconfig._

@Singleton
class MicroserviceAppConfig @Inject() (
  val config: Configuration, val environment: Environment) {

  private def toMap(c: Option[Configuration]) = c.map {
    _.entrySet.toMap.view.mapValues(_.unwrapped.toString)
  }.getOrElse(Map.empty[String, String])

  lazy val fastTrackInjectedParameters: Map[String, String] =
    ConfigSource.default.at("microservice.fasttrack.injectedParameters").loadOrThrow[Map[String, String]]
  lazy val fastStreamInjectedParameters: Map[String, String] =
    ConfigSource.default.at("microservice.faststream.injectedParameters").loadOrThrow[Map[String, String]]
}
