/*
 * Copyright 2019 HM Revenue & Customs
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

import play.api.libs.json.{ JsValue, Json, Writes }

case class NoTemplateFoundError(templateId: String) extends Exception

case class RenderTemplateError(reason: String) extends Exception(reason)

object RenderTemplateError {
  implicit val writes: Writes[RenderTemplateError] = new Writes[RenderTemplateError] {
    override def writes(error: RenderTemplateError): JsValue =
      Json.obj("reason" -> s"Failed to render template: ${error.reason}")
  }
}
