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

package uk.gov.hmrc.fsetemailrenderer.templates.fasttrack

import java.util.Base64

import uk.gov.hmrc.fsetemailrenderer.domain._
import uk.gov.hmrc.fsetemailrenderer.MicroserviceAppConfig

case class FastTrackTemplate(
  templateId: String,
  subject: Subject,
  body: Body
) extends Template {
  val fromAddress: String = new String(Base64.getDecoder.decode(MicroserviceAppConfig.fastTrackInjectedParameters("fromAddress")))
  val priority = MessagePriority.Standard
  val fromService = "fasttrack.gov.uk"
}

object FastTrackTemplateGroup {
  val Templates = Seq(
    FastTrackTemplate(
      templateId = "csr_registration_email",
      subject = Subject("Your activation code"),
      body = Body(html.csrRegistrationEmail.f, txt.csrRegistrationEmail.f)
    ),
    FastTrackTemplate(
      templateId = "csr_registration_activated_no_password_email",
      subject = Subject("New account has been created"),
      body = Body(html.csrRegistrationActivatedNoPasswordEmail.f, txt.csrRegistrationActivatedNoPasswordEmail.f)
    ),
    FastTrackTemplate(
      templateId = "csr_reset_password_email",
      subject = Subject("Your reset password code"),
      body = Body(html.csrResetPasswordEmail.f, txt.csrResetPasswordEmail.f)
    ),
    FastTrackTemplate(
      templateId = "csr_account_lockout",
      subject = Subject("Your account has been locked"),
      body = Body(html.csrLockoutEmail.f, txt.csrLockoutEmail.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_submit_confirmation",
      subject = Subject("Your apprenticeship application has been submitted"),
      body = Body(html.csrAppSubmitted.f, txt.csrAppSubmitted.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_online_test_invitation",
      subject = Subject("Online tests are now open"),
      body = Body(html.csrAppOnlineTestInvitation.f, txt.csrAppOnlineTestInvitation.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_online_test_expired",
      subject = Subject("Time has run out for your online tests"),
      body = Body(html.csrAppOnlineTestExpired.f, txt.csrAppOnlineTestExpired.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_online_test_failed",
      subject = Subject("Online test results"),
      body = Body(html.csrAppOnlineTestFailed.f, txt.csrAppOnlineTestFailed.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_confirm_attendance",
      subject = Subject("You need to confirm your assessment date"),
      body = Body(html.csrAppConfirmAttendance.f, txt.csrAppConfirmAttendance.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_confirm_attendance_reminder",
      subject = Subject("Last chance to confirm your assessment date"),
      body = Body(html.csrAssessmentConfirmationReminder.f, txt.csrAssessmentConfirmationReminder.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_assessment_centre_passed",
      subject = Subject("Assessment centre results"),
      body = Body(html.csrAssessmentCentrePassed.f, txt.csrAssessmentCentrePassed.f)
    ),
    FastTrackTemplate(
      templateId = "csr_app_assessment_centre_failed",
      subject = Subject("Assessment centre results"),
      body = Body(html.csrAssessmentCentreFailed.f, txt.csrAssessmentCentreFailed.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_registration_email",
      subject = Subject("Your activation code"),
      body = Body(html.csrRegistrationEmail.f, txt.csrRegistrationEmail.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_registration_activated_no_password_email",
      subject = Subject("New account has been created"),
      body = Body(html.csrRegistrationActivatedNoPasswordEmail.f, txt.csrRegistrationActivatedNoPasswordEmail.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_reset_password_email",
      subject = Subject("Your reset password code"),
      body = Body(html.csrResetPasswordEmail.f, txt.csrResetPasswordEmail.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_account_lockout",
      subject = Subject("Your account has been locked"),
      body = Body(html.csrLockoutEmail.f, txt.csrLockoutEmail.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_account_email_changed_sent_to_old_address",
      subject = Subject("Your email address has been changed"),
      body = Body(html.csrEmailChangedSentToOldAddress.f, txt.csrEmailChangedSentToOldAddress.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_assessor_email_changed_sent_to_new_address",
      subject = Subject("Your email address has been changed"),
      body = Body(html.csrEmailChangedSentToNewAddress.f, txt.csrEmailChangedSentToNewAddress.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_submit_confirmation",
      subject = Subject("Your apprenticeship application has been submitted"),
      body = Body(html.csrAppSubmitted.f, txt.csrAppSubmitted.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_online_test_invitation",
      subject = Subject("Online tests are now open"),
      body = Body(html.csrAppOnlineTestInvitation.f, txt.csrAppOnlineTestInvitation.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_online_test_expired",
      subject = Subject("Time has run out for your online tests"),
      body = Body(html.csrAppOnlineTestExpired.f, txt.csrAppOnlineTestExpired.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_online_test_failed",
      subject = Subject("Online test results"),
      body = Body(html.csrAppOnlineTestFailed.f, txt.csrAppOnlineTestFailed.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_confirm_attendance",
      subject = Subject("You need to confirm your assessment date"),
      body = Body(html.csrAppConfirmAttendance.f, txt.csrAppConfirmAttendance.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_confirm_attendance_reminder",
      subject = Subject("Last chance to confirm your assessment date"),
      body = Body(html.csrAssessmentConfirmationReminder.f, txt.csrAssessmentConfirmationReminder.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_assessment_centre_passed",
      subject = Subject("Assessment centre results"),
      body = Body(html.csrAssessmentCentrePassed.f, txt.csrAssessmentCentrePassed.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_assessment_centre_failed",
      subject = Subject("Assessment centre results"),
      body = Body(html.csrAssessmentCentreFailed.f, txt.csrAssessmentCentreFailed.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_online_test_reminder_3_day",
      subject = Subject("You need to finish your online exercises"),
      body = Body(html.csrAppOnlineTestReminderThreeDay.f, txt.csrAppOnlineTestReminderThreeDay.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_app_online_test_reminder_1_day",
      subject = Subject("URGENT: You need to finish your online exercises"),
      body = Body(html.csrAppOnlineTestReminderOneDay.f, txt.csrAppOnlineTestReminderOneDay.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_adjustments_confirmation",
      subject = Subject("We've confirmed your adjustments"),
      body = Body(html.csrAdjustmentsConfirmed.f, txt.csrAdjustmentsConfirmed.f)
    ),
    FastTrackTemplate(
      templateId = "fset_fasttrack_adjustments_changed",
      subject = Subject("Your adjustments have been updated"),
      body = Body(html.csrAdjustmentsChanged.f, txt.csrAdjustmentsChanged.f)
    )
  )
}
