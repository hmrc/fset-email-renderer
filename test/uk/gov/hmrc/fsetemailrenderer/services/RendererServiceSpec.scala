/*
 * Copyright 2017 HM Revenue & Customs
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
  }

}
