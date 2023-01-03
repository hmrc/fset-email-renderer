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

package uk.gov.hmrc.fsetemailrenderer.templates.faststream

import java.util.Base64

import uk.gov.hmrc.fsetemailrenderer.domain._
import uk.gov.hmrc.fsetemailrenderer.MicroserviceAppConfig
import uk.gov.hmrc.fsetemailrenderer.templates.faststream

case class FastStreamTemplate(
  config: MicroserviceAppConfig,
  templateId: String,
  subject: Subject,
  body: Body
) extends Template {
  val fromAddress: String = new String(Base64.getDecoder.decode(config.fastStreamInjectedParameters("fromAddress")))
  val priority = MessagePriority.Urgent
  val fromService = "faststream.gov.uk"
}

object FastStreamTemplateGroup {
  // scalastyle:off method.length
  def templates(config: MicroserviceAppConfig) = Seq(
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_registration_email",
      subject = Subject("Your activation code"),
      body = Body(html.fsetFaststreamRegistrationEmail.f, txt.fsetFaststreamRegistrationEmail.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_registration_activated_no_password_email",
      subject = Subject("New account has been created"),
      body = Body(html.fsetFaststreamRegistrationActivatedNoPasswordEmail.f, txt.fsetFaststreamRegistrationActivatedNoPasswordEmail.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_external_registration_activated_no_password_email",
      subject = Subject("Your account has been created"),
      body = Body(html.fsetFaststreamExternalRegistrationActivatedNoPasswordEmail.f,
        txt.fsetFaststreamExternalRegistrationActivatedNoPasswordEmail.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_reset_password_email",
      subject = Subject("Your reset password code"),
      body = Body(html.fsetFaststreamResetPasswordEmail.f, txt.fsetFaststreamResetPasswordEmail.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_account_lockout",
      subject = Subject("Your account has been locked"),
      body = Body(html.fsetFaststreamLockoutEmail.f, txt.fsetFaststreamLockoutEmail.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_account_email_changed_sent_to_old_address",
      subject = Subject("Your email address has been changed"),
      body = Body(html.fsetFaststreamEmailChangedSentToOldAddress.f, txt.fsetFaststreamEmailChangedSentToOldAddress.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_assessor_email_changed_sent_to_new_address",
      subject = Subject("Your email address has been changed"),
      body = Body(html.fsetFaststreamEmailChangedSentToNewAddress.f, txt.fsetFaststreamEmailChangedSentToNewAddress.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_submit_confirmation",
      subject = Subject("You've successfully submitted your application"),
      body = Body(html.fsetFaststreamAppSubmittedEmail.f, txt.fsetFaststreamAppSubmittedEmail.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_test_invitation",
      subject = Subject("You can start your online exercises now"),
      body = Body(html.fsetFaststreamAppOnlineTestInvitation.f, txt.fsetFaststreamAppOnlineTestInvitation.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_phase2_test_invitation",
      subject = Subject("You can start your workbased scenarios exercise now"),
      body = Body(html.fsetFaststreamAppPhase2TestInvitation.f, txt.fsetFaststreamAppPhase2TestInvitation.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_phase3_test_invitation",
      subject = Subject("You can start the video interview now"),
      body = Body(html.fsetFaststreamAppPhase3TestInvitation.f, txt.fsetFaststreamAppPhase3TestInvitation.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase1_test_expired",
      subject = Subject("Your online exercises have expired"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestExpired.f, txt.fsetFaststreamAppOnlinePhase1TestExpired.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase2_test_expired",
      subject = Subject("Your workbased scenarios have expired"),
      body = Body(html.fsetFaststreamAppOnlinePhase2TestExpired.f, txt.fsetFaststreamAppOnlinePhase2TestExpired.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase3_test_expired",
      subject = Subject("Your video interview has expired"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestExpired.f, txt.fsetFaststreamAppOnlinePhase3TestExpired.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase1_test_reminder",
      subject = Subject("You need to finish your online exercises"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestReminder.f, txt.fsetFaststreamAppOnlinePhase1TestReminder.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase2_test_reminder",
      subject = Subject("You need to finish the workbased scenarios"),
      body = Body(html.fsetFaststreamAppOnlinePhase2TestReminder.f, txt.fsetFaststreamAppOnlinePhase2TestReminder.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase3_test_reminder",
      subject = Subject("You need to finish the video interview"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestReminder.f, txt.fsetFaststreamAppOnlinePhase3TestReminder.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase1_test_success",
      subject = Subject("You've passed the online tests"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestSuccess.f, txt.fsetFaststreamAppOnlinePhase1TestSuccess.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase1_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase1TestFailed.f, txt.fsetFaststreamAppOnlinePhase1TestFailed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase2_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase2TestFailed.f, txt.fsetFaststreamAppOnlinePhase2TestFailed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase3_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestFailed.f, txt.fsetFaststreamAppOnlinePhase3TestFailed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_sdip_fs_test_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlineSdipFsTestFailed.f, txt.fsetFaststreamAppOnlineSdipFsTestFailed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase3_test_success",
      subject = Subject("You've passed the video interview"),
      body = Body(html.fsetFaststreamAppOnlinePhase3TestSuccess.f, txt.fsetFaststreamAppOnlinePhase3TestSuccess.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_phase3_test_success_sdipfs",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppOnlinePhase3SdipFsTestSuccess.f, txt.fsetFaststreamAppOnlinePhase3SdipFsTestSuccess.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_online_fast-pass_accepted",
      subject = Subject("We've confirmed your Fast Pass"),
      body = Body(html.fsetFaststreamAppOnlineFastPassAccepted.f, txt.fsetFaststreamAppOnlineFastPassAccepted.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_withdrawn",
      subject = Subject("Your application has been withdrawn"),
      body = Body(html.fsetFaststreamAppWithdrawn.f, txt.fsetFaststreamAppWithdrawn.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_adjustments_confirmation",
      subject = Subject("We've confirmed your adjustments"),
      body = Body(html.fsetFaststreamAdjustmentsConfirmed.f, txt.fsetFaststreamAdjustmentsConfirmed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_adjustments_changed",
      subject = Subject("Your adjustments have been updated"),
      body = Body(html.fsetFaststreamAdjustmentsChanged.f, txt.fsetFaststreamAdjustmentsChanged.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_converted_to_sdip_confirmation",
      subject = Subject("We'll consider you for SIP"),
      body = Body(html.fsetFaststreamAppConvertedToSdip.f, txt.fsetFaststreamAppConvertedToSdip.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_remind_unsubmitted_assessors",
      subject = Subject("You need to submit your availability"),
      body = Body(html.fsetFaststreamRemindUnsubmittedAssessors.f, txt.fsetFaststreamRemindUnsubmittedAssessors.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_notify_event_assessor_allocated",
      subject = Subject("You've been assigned an event"),
      body = Body(html.fsetFaststreamNotifyEventAssessorAllocated.f, txt.fsetFaststreamNotifyEventAssessorAllocated.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_notify_event_assessor_unallocated",
      subject = Subject("You've been removed from an event"),
      body = Body(html.fsetFaststreamNotifyEventAssessorUnAllocated.f, txt.fsetFaststreamNotifyEventAssessorUnAllocated.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_notify_event_assessor_allocation_changed",
      subject = Subject("One of your events has changed"),
      body = Body(html.fsetFaststreamNotifyEventAssessorAllocationChanged.f, txt.fsetFaststreamNotifyEventAssessorAllocationChanged.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_candidate_need_confirm_assessment_date",
      subject = Subject("You need to confirm your assessment date"),
      body = Body(html.fsetFaststreamCandidateAllocateConfirmationRequest.f, txt.fsetFaststreamCandidateAllocateConfirmationRequest.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_candidate_need_confirm_assessment_date_reminder",
      subject = Subject("URGENT - You need to confirm your assessment date"),
      body = Body(html.fsetFaststreamCandidateAllocateConfirmationRequestReminder.f,
        txt.fsetFaststreamCandidateAllocateConfirmationRequestReminder.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_candidate_assessment_scheduled",
      subject = Subject("Your assessment has been scheduled"),
      body = Body(html.fsetFaststreamCandidateAllocatedConfirmed.f, txt.fsetFaststreamCandidateAllocatedConfirmed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_candidate_assessment_centre_completed",
      subject = Subject("You've passed the Fast Stream Assessment Centre"),
      body = Body(html.fsetFaststreamCandidateAssessmentCentreCompleted.f, txt.fsetFaststreamCandidateAssessmentCentreCompleted.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_notify_event_candidate_unallocated",
      subject = Subject("You've been removed from an event"),
      body = Body(html.fsetFaststreamNotifyEventCandidateUnAllocated.f, txt.fsetFaststreamNotifyEventCandidateUnAllocated.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_notify_assessors_of_new_events",
      subject = Subject("New events: Share your availability"),
      body = Body(html.fsetFaststreamNotifyAssessorOfNewEvents.f, txt.fsetFaststreamNotifyAssessorOfNewEvents.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_notify_candidate_sift_entered_additional_questions",
      subject = Subject("You need to answer some additional questions"),
      body = Body(html.fsetFaststreamNotifyCandidateSiftEnteredAdditionalQuestions.f,
        txt.fsetFaststreamNotifyCandidateSiftEnteredAdditionalQuestions.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_sift_numeric_test_invitation",
      subject = Subject("You can start your additional information numeric test now"),
      body = Body(html.fsetFaststreamSiftNumericTestInvitation.f, txt.fsetFaststreamSiftNumericTestInvitation.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_sift_reminder",
      subject = Subject("You need to submit your additional information details"),
      body = Body(html.fsetFaststreamSiftReminder.f, txt.fsetFaststreamSiftReminder.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_sift_expired",
      subject = Subject("Time has run out to submit your additional information details"),
      body = Body(html.fsetFaststreamSiftExpired.f, txt.fsetFaststreamSiftExpired.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_final_failed",
      subject = Subject("We've assessed your results"),
      body = Body(html.fsetFaststreamAppFinalFailed.f, txt.fsetFaststreamAppFinalFailed.f)
    ),
    FastStreamTemplate(
      config,
      templateId = "fset_faststream_app_final_success",
      subject = Subject("We're delighted to be able to offer you a role on the Civil Service Fast Stream"),
      body = Body(html.fsetFaststreamAppFinalSuccess.f, txt.fsetFaststreamAppFinalSuccess.f)
    )
  )
  // scalastyle:on method.length
}
