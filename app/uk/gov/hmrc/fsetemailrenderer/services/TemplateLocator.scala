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

package uk.gov.hmrc.fsetemailrenderer.services

import uk.gov.hmrc.fsetemailrenderer.MicroserviceAppConfig
import uk.gov.hmrc.fsetemailrenderer.domain.Template
import uk.gov.hmrc.fsetemailrenderer.templates.fasttrack.FastTrackTemplateGroup
import uk.gov.hmrc.fsetemailrenderer.templates.faststream.FastStreamTemplateGroup

trait TemplateLocator {
  def templates(config: MicroserviceAppConfig): Seq[Template]

  def findTemplate(config: MicroserviceAppConfig, templateId: String) : Option[Template] = {
    templates(config).find(_.templateId == templateId)
  }
}

object TemplateLocator extends TemplateLocator {
  override def templates(config: MicroserviceAppConfig): Seq[Template with Product with Serializable] = {
    FastTrackTemplateGroup.templates(config) ++ FastStreamTemplateGroup.templates(config)
  }
}
