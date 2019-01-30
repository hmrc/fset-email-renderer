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

package uk.gov.hmrc.fsetemailrenderer.services

import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.PlaySpec
import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params
import uk.gov.hmrc.fsetemailrenderer.domain.{ NoTemplateFoundError, RenderResult, RenderTemplateError }
import uk.gov.hmrc.fsetemailrenderer.templates.faststream.FastStreamTemplateGroup
import uk.gov.hmrc.play.test.WithFakeApplication

class RendererServiceSpec extends PlaySpec with ScalaFutures with WithFakeApplication {

  val service = RendererService

  "Render service" must {
    "render a template" in {

      val params = Params(Map("name" -> "Dr. Bruce Banner", "activationCode" -> "AABBCC", "programme" -> "fasttrack"))
      val actual = service.render("fset_fasttrack_registration_email", params).futureValue

      actual mustBe a[RenderResult]
    }
    "throw an exception if the programme is not found" in {
      val params = Params(Map("name" -> "Dr. Bruce Banner", "activationCode" -> "AABBCC"))
      val actual = service.render("fset_fasttrack_registration_email", params).failed.futureValue

      actual mustBe RenderTemplateError("key not found: programme")
    }

    "throw an exception if the template is not found" in {
      val params = Params(Map("name" -> "Dr. Bruce Banner", "activationCode" -> "AABBCC", "programme" -> "fasttrack"))
      val actual = service.render("some random template", params).failed.futureValue

      actual mustBe NoTemplateFoundError("some random template")
    }

    "render valid email for each template" in {
      val allParams = Params(Map("name" -> "Mr. Bin", "programme" -> "ABC", "activationCode" -> "XXX",
        "resetPasswordCode" -> "9011", "newEmail" -> "true", "expireDateTime" -> "01/01/21", "timeLeft" -> "96",
        "timeUnit" -> "ss", "etrayAdjustments" -> "true", "videoAdjustments" -> "y",
        "externalSigninUrl" -> "http://esu", "candidateSigninUrl" -> "http://esu", "eventGuideUrl" -> "http://fifi",
        "eventDate" -> "2 November 1920", "eventRole" -> "Assessor", "eventRoleKey" -> "ASSESSOR", "eventName" -> "London Assessment Centre",
        "eventLocation" -> "London", "eventStartTime" -> "9am", "deadlineDate" -> "22 June 1939",
        "eventType" -> "FSAC", "eventVenue" -> "London, Bush House", "htmlBody" -> "<ul><li>Event 1</li></ul>",
        "txtBody" -> "Event 1", "scheme" -> "GORC"))

      val accost = s"Dear ${allParams.parameters("name")}"

      FastStreamTemplateGroup.Templates.foreach(t => {
        val res = service.render(t.templateId, allParams).futureValue
        res mustBe a[RenderResult]
        res match {
          case rr: RenderResult =>
            rr.subject mustBe t.subject.text
            rr.html must include(htmlEncode(t.subject.text))
            rr.html must include(accost)
            rr.plain must include(accost)
        }
      })
    }
  }

  private def htmlEncode(text: String) = text.map {
    case '\'' => "&#x27;"
    case z => z
  }.mkString("")

}
