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

package uk.gov.hmrc.fsetemailrenderer.services

import play.api.Logging

import javax.inject.{Inject, Singleton}
import uk.gov.hmrc.fsetemailrenderer.MicroserviceAppConfig
import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params
import uk.gov.hmrc.fsetemailrenderer.domain.*

import scala.concurrent.Future
import scala.util.Failure

@Singleton
class RendererService @Inject() (config: MicroserviceAppConfig) extends Logging {

  def templateLocator: TemplateLocator = TemplateLocator

  private def getInjectedParameters(programme: String) = {
    val parameters = if (programme == "faststream") {
      config.fastStreamInjectedParameters
    } else {
      config.fastTrackInjectedParameters
    }
//    logger.debug(s"Parameters read from config = $parameters")
    parameters
  }

  def render(templateId: String, params: Params): Future[RenderResult] = Future.fromTry {
    params.parameters.get("programme").map { programme =>
      templateLocator.findTemplate(config, templateId).map { template =>
        val additionalParams = getInjectedParameters(programme)
        template.render(params.copy(parameters = params.parameters ++ additionalParams)) match {
          case Failure(error) => Failure(RenderTemplateError(error.getMessage))
          case s => s
        }
      }.getOrElse(Failure(NoTemplateFoundError(templateId)))
    }.getOrElse(Failure(RenderTemplateError("key not found: programme")))
  }
}
