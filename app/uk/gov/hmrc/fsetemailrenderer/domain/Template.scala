/*
 * Copyright 2020 HM Revenue & Customs
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

import play.twirl.api.{ Appendable, Html, Txt }
import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params
import uk.gov.hmrc.fsetemailrenderer.domain.MessagePriority.MessagePriority

import scala.util.Try

trait Template {

  def templateId: String
  def fromAddress: String
  def subject: Subject
  def body: Body
  def priority: MessagePriority
  def fromService: String

  def render(params: Params): Try[RenderResult] = Try {
    RenderResult(
      body.text(params).toString,
      body.html(params).toString,
      fromAddress,
      subject.text,
      fromService,
      priority
    )
  }
}

case class Subject(text: String)

case class Body(html: Params => Appendable[Html], text: Params => Appendable[Txt])
