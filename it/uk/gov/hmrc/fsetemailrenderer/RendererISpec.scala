package uk.gov.hmrc.fsetemailrenderer

import org.scalatest.matchers.dsl.EmptyWord
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play.{PlaySpec, WsScalaTestClient}
import play.api.http.Status
import play.api.libs.json.Json
import play.api.test.Helpers._

import java.nio.charset.StandardCharsets
import java.util.Base64

class RendererISpec extends PlaySpec with GuiceOneServerPerSuite with WsScalaTestClient {
  implicit val wsClient: play.api.libs.ws.WSClient =
    app.injector.instanceOf[play.api.libs.ws.WSClient]

  "email renderer" should {
    val programme = "faststream"

    "render the html and text content for fast stream registration template" in {
      val name = "Dr. Bruce Banner"
      val result = await(wsUrl("/templates/fset_faststream_registration_email")
        .post(Json.obj("parameters" -> Json.obj("name" -> name, "activationCode" -> "AABBCC", "programme" -> programme))))

      result.status mustBe Status.OK
      (result.json \ "fromAddress").as[String] mustBe "Fast Stream team <noreply@csr.vtdev.uk>"
      (result.json \ "subject").as[String] mustBe "Your activation code"
      (result.json \ "service").as[String] mustBe "faststream.gov.uk"

      val plainText = (result.json \ "plain").as[String]
      validateContent(plainText, name)

      val htmlText = (result.json \ "html").as[String]
      validateContent(htmlText, name)

      (result.json \ "priority").as[String] mustBe "urgent"
    }

    "return NOT_FOUND if template does not exist" in {
      val result = await(wsUrl("/templates/notExistTemplate")
        .post(Json.obj("parameters" -> Json.obj("name" -> "Dr. Bruce Banner", "programme" -> programme))))
      result.status mustBe NOT_FOUND
    }

    "return BAD_REQUEST if a required parameter is not in the request body" in {
      val result = await(wsUrl("/templates/fset_faststream_registration_email")
        .post(Json.obj("parameters" -> Json.obj("name" -> "Dr. Bruce Banner", "programme" -> programme))))
      result.status mustBe BAD_REQUEST
      (result.json \ "reason").as[String] mustBe "Failed to render template: key not found: activationCode"
    }
  }

  def base64Decode(value: String) = new String(Base64.getDecoder.decode(value), StandardCharsets.UTF_8)

  def validateContent(content: String, expectedName: String) = {
    content mustNot be(new EmptyWord())
    val contentDecoded = base64Decode(content)
    contentDecoded must include(s"Dear $expectedName")
  }
}
