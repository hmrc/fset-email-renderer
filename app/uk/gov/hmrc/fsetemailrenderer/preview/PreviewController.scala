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


import play.api.libs.json.Json
import play.api.mvc.{ Action, AnyContent }
import play.twirl.api.Html
import uk.gov.hmrc.fsetemailrenderer.controllers.model.Params
import uk.gov.hmrc.fsetemailrenderer.services.{ RendererService, TemplateLocator }
import uk.gov.hmrc.fsetemailrenderer.domain.Template
import uk.gov.hmrc.play.microservice.controller.BaseController

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object PreviewController extends PreviewController {
  val templates: Seq[Template] = TemplateLocator.templates
  val rendererService: RendererService = RendererService
  val templateParams: TemplateParams = TemplateParams
}

trait PreviewController extends BaseController {

  def templates: Seq[Template]

  def templateParams: TemplateParams

  def rendererService: RendererService

  def preview: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.preview(templates)))
  }

  def previewHtml(templateId: String): Action[AnyContent] = Action.async { implicit request =>
    rendererService.render(templateId, getParams(templateId, request.queryString)).map { r =>
      Ok(Html(r.html))
    } recover {
      case error => BadRequest(Json.toJson(error.getMessage))
    }
  }

  def previewHtmlSource(templateId: String): Action[AnyContent] = Action.async { implicit request =>
    rendererService.render(templateId, getParams(templateId, request.queryString)).map { r =>
      Ok(r.html)
    } recover {
      case error => BadRequest(Json.toJson(error.getMessage))
    }
  }

  def previewText(templateId: String): Action[AnyContent] = Action.async { implicit request =>
    rendererService.render(templateId, getParams(templateId, request.queryString)).map { r =>
      Ok(r.plain)
    } recover {
      case error => BadRequest(Json.toJson(error.getMessage))
    }
  }

  private def getParams(templateId: String, queryString: Map[String, Seq[String]]): Params = {
    Params(templateParams.paramsFor(templateId).parameters ++ queryString.flatMap { case (k, v) => Map(k -> v.mkString(",")) })
  }

}
