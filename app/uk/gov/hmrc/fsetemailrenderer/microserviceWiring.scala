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

import org.apache.pekko.actor.ActorSystem
import com.typesafe.config.Config
import javax.inject.{Inject, Singleton}
import play.api.Application
import play.api.libs.ws.WSClient
import uk.gov.hmrc.http._
import uk.gov.hmrc.http.hooks.HttpHooks
import uk.gov.hmrc.play.http.ws._

@Singleton
class WSHttp @Inject() (
  val wsClient: WSClient,
  application: Application)
  extends HttpGet
    with WSGet
    with HttpPut
    with WSPut
    with HttpPost
    with WSPost
    with HttpDelete
    with WSDelete
    with HttpPatch with WSPatch with HttpHooks {
  override val hooks = NoneRequired
  override lazy val configuration: Config = application.configuration.underlying
  override lazy val actorSystem: ActorSystem = application.actorSystem
}
