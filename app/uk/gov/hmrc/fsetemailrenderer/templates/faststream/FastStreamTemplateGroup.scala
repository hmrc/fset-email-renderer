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

package uk.gov.hmrc.fsetemailrenderer.templates.faststream

import java.util.Base64

import uk.gov.hmrc.fsetemailrenderer.domain._
import uk.gov.hmrc.fsetemailrenderer.MicroserviceAppConfig

case class FastStreamTemplate(
  templateId: String,
  subject: Subject,
  body: Body
) extends Template {
  val fromAddress: String = new String(Base64.getDecoder.decode(MicroserviceAppConfig.fastStreamInjectedParameters("fromAddress")))
  val priority = MessagePriority.Standard
  val fromService = "faststream.gov.uk"
}

object FastStreamTemplateGroup {
  val Templates = Seq(
    FastStreamTemplate(
      templateId = "fset_faststream_registration_email",
      subject = Subject("Your activation code"),
      body = Body(html.fsetFaststreamRegistrationEmail.f, txt.fsetFaststreamRegistrationEmail.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_registration_activated_no_password_email",
      subject = Subject("New account has been created"),
      body = Body(html.fsetFaststreamRegistrationActivatedNoPasswordEmail.f, txt.fsetFaststreamRegistrationActivatedNoPasswordEmail.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_external_registration_activated_no_password_email",
      subject = Subject("New account has been created"),
      body = Body(html.fsetFaststreamExternalRegistrationActivatedNoPasswordEmail.f, txt.fsetFaststreamExternalRegistrationActivatedNoPasswordEmail.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_reset_password_email",
      subject = Subject("Your reset password code"),
      body = Body(html.fsetFaststreamResetPasswordEmail.f, txt.fsetFaststreamResetPasswordEmail.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_account_lockout",
      subject = Subject("Your account has been locked"),
      body = Body(html.fsetFaststreamLockoutEmail.f, txt.fsetFaststreamLockoutEmail.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_submit_confirmation",
      subject = Subject("You've successfully submitted your application"),
      body = Body(html.fsetFaststreamAppSubmittedEmail.f, txt.fsetFaststreamAppSubmittedEmail.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_test_invitation",
      subject = Subject("You can start your online exercises now"),
      body = Body(html.fsetFaststreamAppOnlineTestInvitation.f, txt.fsetFaststreamAppOnlineTestInvitation.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_phase2_test_invitation",
      subject = Subject("You can start your etray exercise now"),
      body = Body(html.fsetFaststreamAppPhase2TestInvitation.f, txt.fsetFaststreamAppPhase2TestInvitation.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_phase3_test_invitation",
      subject = Subject("You can start the video interview now"),
      body = Body(html.fsetFaststreamAppPhase3TestInvitation.f, txt.fsetFaststreamAppPhase3TestInvitation.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase1_test_expired",
      subject = Subject("Your online exercises have expired"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestExpired.f, txt.fsetFaststreamAppOnlinePhase1TestExpired.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase2_test_expired",
      subject = Subject("Your e-tray has expired"),
      body = Body(html.fsetFaststreamAppOnlinePhase2TestExpired.f, txt.fsetFaststreamAppOnlinePhase2TestExpired.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase3_test_expired",
      subject = Subject("Your video interview has expired"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestExpired.f, txt.fsetFaststreamAppOnlinePhase3TestExpired.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase1_test_reminder",
      subject = Subject("You need to finish your online exercises"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestReminder.f, txt.fsetFaststreamAppOnlinePhase1TestReminder.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase2_test_reminder",
      subject = Subject("You need to finish the e-tray"),
      body = Body(html.fsetFaststreamAppOnlinePhase2TestReminder.f, txt.fsetFaststreamAppOnlinePhase2TestReminder.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase3_test_reminder",
      subject = Subject("You need to finish the video interview"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestReminder.f, txt.fsetFaststreamAppOnlinePhase3TestReminder.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase1_test_success",
      subject = Subject("You've passed the online tests"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestSuccess.f, txt.fsetFaststreamAppOnlinePhase1TestSuccess.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase1_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestFailed.f, txt.fsetFaststreamAppOnlinePhase1TestFailed.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase2_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase2TestFailed.f, txt.fsetFaststreamAppOnlinePhase2TestFailed.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase3_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestFailed.f, txt.fsetFaststreamAppOnlinePhase3TestFailed.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_sdip_fs_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlineSdipFsTestFailed.f, txt.fsetFaststreamAppOnlineSdipFsTestFailed.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_sdip_fs_test_success",
      subject = Subject("You can now access your interview account for SDIP"),
      body = Body(html.fsetFaststreamAppOnlineSdipFsTestSuccess.f, txt.fsetFaststreamAppOnlineSdipFsTestSuccess.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_phase3_test_success",
      subject = Subject("You've passed the video interview"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestSuccess.f, txt.fsetFaststreamAppOnlinePhase3TestSuccess.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_online_fast-pass_accepted",
      subject = Subject("We've confirmed your Fast Pass"),
      body = Body(html.fsetFaststreamAppOnlineFastPassAccepted.f, txt.fsetFaststreamAppOnlineFastPassAccepted.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_withdrawn",
      subject = Subject("Your application has been withdrawn"),
      body = Body(html.fsetFaststreamAppWithdrawn.f, txt.fsetFaststreamAppWithdrawn.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_adjustments_confirmation",
      subject = Subject("We've confirmed your adjustments"),
      body = Body(html.fsetFaststreamAdjustmentsConfirmed.f, txt.fsetFaststreamAdjustmentsConfirmed.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_adjustments_changed",
      subject = Subject("Your adjustments have been updated"),
      body = Body(html.fsetFaststreamAdjustmentsChanged.f, txt.fsetFaststreamAdjustmentsChanged.f)
    ),
    FastStreamTemplate(
      templateId = "fset_faststream_app_converted_to_sdip_confirmation",
      subject = Subject("We'll consider you for SDIP"),
      body = Body(html.fsetFaststreamAppConvertedToSdip.f, txt.fsetFaststreamAppConvertedToSdip.f)
    )
  )
}
