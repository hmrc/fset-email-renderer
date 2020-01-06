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

package uk.gov.hmrc.fsetemailrenderer.preview

import java.time.{ LocalDate, LocalDateTime }

import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params

object TemplateParams extends TemplateParams {
}

trait TemplateParams {
  def paramsFor(templateId: String): Params = sampleTemplateParameters(templateId)

  private val defaultParameters = Map(
    "name" -> "Preferred name",
    "eventRole" -> "Event Role",
    "eventRoleKey" -> "Event Role Key",
    "eventName" -> "Event Name",
    "eventType" -> "Event Type",
    "eventVenue" -> "Event Venue",
    "eventLocation" -> "Event Location",
    "eventGuideUrl" -> "Event Guide Url",
    "eventDate" ->  LocalDate.now().toString,
    "eventStartTime" -> LocalDateTime.now().toString,
    "assessmentDateTime" -> LocalDateTime.now().toString,
    "deadlineDate" -> LocalDate.now().toString,
    "expireDateTime" -> LocalDateTime.now().toString,
    "confirmByDate" -> LocalDate.now().toString,
    "activationCode" -> "ACTIVA",
    "resetPasswordCode" -> "RESETPA",
    "newEmail" -> "newemail@example.com",
    "onlineTestsAdjustments" -> "Online test adjustments",
    "assessmentCenterAdjustments" -> "Assessment center adjustments",
    "scheme" -> "Scheme"
  )

  private val sampleTemplateParameters: Map[String, Params] = Map(
    "sample1" -> Params(Map("name" -> "Mr. Hyde")),
    "sample2" -> Params(Map("name" -> "Dr. Jekyll"))
  ).withDefaultValue(Params(defaultParameters))

}
