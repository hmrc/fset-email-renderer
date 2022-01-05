/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.fsetemailrenderer.domain

import java.nio.charset.StandardCharsets
import play.api.libs.json.{JsValue, Json, Writes}
import java.util.Base64
import uk.gov.hmrc.fsetemailrenderer.domain.MessagePriority.MessagePriority

case class RenderResult(plain: String,
                        html: String,
                        fromAddress: String,
                        subject: String,
                        service: String,
                        priority: MessagePriority) {
  override def toString = s"plain=$plain,html=$html,fromAddress=$fromAddress," +
    s"subject=$subject,service=$service,priority=$priority"
}

object RenderResult {

  private def base64Encoded(value: String) = Base64.getEncoder.encodeToString(value.getBytes(StandardCharsets.UTF_8))

  implicit val writes = new Writes[RenderResult] {
    override def writes(r: RenderResult): JsValue = Json.obj(
      "plain"       -> base64Encoded(r.plain),
      "html"        -> base64Encoded(r.html),
      "fromAddress" -> r.fromAddress,
      "subject"     -> r.subject,
      "service"     -> r.service,
      "priority"    -> r.priority.toString
    )
  }
}
