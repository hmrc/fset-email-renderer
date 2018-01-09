/*
 * Copyright 2018 HM Revenue & Customs
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

import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params
import uk.gov.hmrc.fsetemailrenderer.domain._
import uk.gov.hmrc.fsetemailrenderer.MicroserviceAppConfig._

import scala.concurrent.Future
import scala.util.{ Failure, Success }


object RendererService extends RendererService {
  override def templateLocator: TemplateLocator = TemplateLocator
}

trait RendererService {

  def templateLocator: TemplateLocator

  private def getInjectedParameters(programme: String) = if (programme == "faststream") {
    fastStreamInjectedParameters
  } else {
    fastTrackInjectedParameters
  }

  def render(templateId: String, params: Params): Future[RenderResult] = Future.fromTry {
    params.parameters.get("programme").map { programme =>
      templateLocator.findTemplate(templateId).map { template =>
        val additionalParams = getInjectedParameters(programme)
        template.render(params.copy(parameters = params.parameters ++ additionalParams)) match {
          case Failure(error) => Failure(RenderTemplateError(error.getMessage))
          case s => s
        }
      }.getOrElse(Failure(NoTemplateFoundError(templateId)))
    }.getOrElse(Failure(RenderTemplateError("key not found: programme")))
  }
}
