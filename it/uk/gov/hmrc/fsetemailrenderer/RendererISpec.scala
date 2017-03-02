package uk.gov.hmrc.fsetemailrenderer

import org.scalatest.words.EmptyWord
import org.scalatestplus.play.{OneServerPerSuite, PlaySpec, WsScalaTestClient}
import play.api.http.Status
import play.api.libs.json.Json
import play.api.test.Helpers._

class RendererISpec extends PlaySpec with OneServerPerSuite with WsScalaTestClient {

  "email renderer" should {
    "render the html and text content for fast track registration template" in {
      val result = await(wsUrl("/templates/fset_fasttrack_registration_email").
        post(Json.obj("parameters" -> Json.obj("name" -> "Dr. Bruce Banner", "activationCode" -> "AABBCC", "programme" -> "fasttrack"))))

      result.status mustBe Status.OK
      (result.json \ "fromAddress").as[String] mustBe "noreply@csr.vtdev.uk"
      (result.json \ "subject").as[String] mustBe "Your activation code"
      (result.json \ "service").as[String] mustBe "fasttrack.gov.uk"
      (result.json \ "plain").as[String] mustNot be(new EmptyWord())
      (result.json \ "html").as[String] mustNot be(new EmptyWord())
      (result.json \ "priority").as[String] mustBe "standard"
    }

    "return NOT_FOUND if template does not exist" in {
      val result = await(wsUrl("/templates/notExistTemplate").
        post(Json.obj("parameters" -> Json.obj("name" -> "Dr. Bruce Banner", "programme" -> "fasttrack"))))
      result.status mustBe NOT_FOUND
    }

    "return BAD_REQUEST if required parameter is not in the request body" in {
      val result = await(wsUrl("/templates/fset_fasttrack_registration_email").
        post(Json.obj("parameters" -> Json.obj("name" -> "Dr. Bruce Banner", "programme" -> "fasttrack"))))
      result.status mustBe BAD_REQUEST
      (result.json \ "reason").as[String] mustBe "Failed to render template: key not found: activationCode"
    }
  }
}
