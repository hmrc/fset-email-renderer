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

package uk.gov.hmrc.fsetemailrenderer.controllers

import play.api.libs.json._
import play.api.mvc._
import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params
import uk.gov.hmrc.fsetemailrenderer.domain.{NoTemplateFoundError, RenderTemplateError}
import uk.gov.hmrc.fsetemailrenderer.services.RendererService
import uk.gov.hmrc.play.microservice.controller.BaseController

import scala.concurrent.ExecutionContext.Implicits.global

object RendererController extends RendererController {
  val rendererService = RendererService
}

trait RendererController extends BaseController {

  def rendererService: RendererService

  def render(templateId: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
      withJsonBody[Params] { params =>

      rendererService.render(templateId, params).map { renderResult =>
        Ok(Json.toJson(renderResult))
      } recover {
        case _: NoTemplateFoundError => NotFound
        case error: RenderTemplateError => BadRequest(Json.toJson(error))
      }
    }
  }
}
